package com.telkom.weedu.view.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.data.api.model.Product;
import com.telkom.weedu.data.api.model.QBacaBook;
import com.telkom.weedu.data.api.model.Recomendation;
import com.telkom.weedu.event.RefreshUnreadMailsEvent;
import com.telkom.weedu.view.adapters.ProductAdapter;
import com.telkom.weedu.view.adapters.RecomendationAdapter;
import com.telkom.weedu.view.detailbook.DetailBookActivity;
import com.telkom.weedu.view.mail.MailActivity;
import com.telkom.weedu.view.main.MainActivity;
import com.telkom.weedu.view.webview.WebViewActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeView {

    @BindView(R.id.rv_recommendations)
    RecyclerView rvRecommendations;
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;
    @BindView(R.id.btn_more_products)
    ImageView btnMoreProducts;
    @BindView(R.id.btn_help_desk)
    ImageView btnHelpDesk;
    @BindView(R.id.ln_inbox)
    LinearLayout lnInbox;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.nv_home_content)
    NestedScrollView nvHomeContent;
    @BindView(R.id.tv_unread_mail)
    TextView tvUnreadMail;

    @BindColor(R.color.colorPrimary)
    int colorPrimary;
    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;
    @BindColor(R.color.white)
    int colorWhite;

    private LinkedList<Product> products;
    private LinkedList<Recomendation> recomendations;
    private ProductAdapter productAdapter;
    private RecomendationAdapter recomendationAdapter;

    @Inject
    IHomePresenter<HomeView> presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        recomendations = new LinkedList<>();

        registerEventBusSubscriber(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupDummyProducts();
        productAdapter = new ProductAdapter(getBaseActivity(), products);
        rvProducts.setLayoutManager(new GridLayoutManager(getBaseActivity(), 3));
        rvProducts.setAdapter(productAdapter);
        rvProducts.setNestedScrollingEnabled(false);

        recomendationAdapter = new RecomendationAdapter(getBaseActivity(), recomendations);
        rvRecommendations.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvRecommendations.setAdapter(recomendationAdapter);

    }

    private void showDataInList(LinkedList<Recomendation> mRecomendations) {
        recomendations.addAll(mRecomendations);
        recomendationAdapter.add(recomendations);
        recomendationAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initAction() {
        productAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.openDetailProduct(products.get(position));
            }
        });
        lnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toEdumailActivity();
            }
        });

        btnHelpDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHelpDeskFragment();
            }
        });

        recomendationAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.openDetailBook(recomendations.get(position).getqBacaBook());
            }
        });
    }

    @Override
    protected void initProcess() {
        getTotalUnreadmail();
        getRecommendationList();
    }

    private void getTotalUnreadmail() {
        presenter.loadUnreadMail();
    }

    private void setupDummyProducts() {
        products = new LinkedList<>();
        products.add(new Product(R.drawable.qbaca, "Qbaca", "E-Book", "https://qbaca.com", false));
        products.add(new Product(R.drawable.qjurnal, "QJournal", "Jurnal", "https://qjournal.id", false));
        products.add(new Product(R.drawable.rumahbelajar, "Rumah Belajar", "Yuk Belajar", "https://belajar.kemdikbud.go.id/Dashboard/", false));
        products.add(new Product(R.drawable.ruangles, "Ruangguru", "Ruangles", "https://ruangguru.com/les", false));
        products.add(new Product(R.drawable.ruanguji, "Ruangguru", "Ruanguji", "https://to.ruangguru.com", false));
        products.add(new Product(R.drawable.ruangvideo, "Ruangguru", "Ruangvideo", "https://ruangguru.com/video", false));
        products.add(new Product(R.drawable.ruanglatihan, "Ruangguru", "Ruanglatihan", "https://ruangguru.com/latihan", false));
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void getRecommendationList() {
        presenter.loadRecomendation();
    }


    @Override
    public void showLoading() {
        super.showLoading();
        progressBar.setVisibility(View.VISIBLE);
        nvHomeContent.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        progressBar.setVisibility(View.GONE);
        nvHomeContent.setVisibility(View.VISIBLE);
    }

    public void toEdumailActivity() {
        MailActivity.start(getActivity());
    }

    public void toHelpDeskFragment() {
        TabLayout.Tab tab = ((MainActivity) getBaseActivity()).getTabMain().getTabAt(2);
        tab.select();
    }

    @Override
    public void showRecommendationResult(LinkedList<Recomendation> recomendations) {
        showDataInList(recomendations);
        hideLoading();
    }

    @Override
    public void showEmptyRecommendationResult(String message) {
        hideLoading();
        showToast(message);
    }

    @Override
    public void showErrorReqcommendationResult(String message) {
        hideLoading();
        showToast(message);
    }

    @Override
    public void showUnreadMailSuccess(int count) {
        String inboxCount = "Inbox (" + count + ")";
        tvUnreadMail.setText(inboxCount);
    }

    @Override
    public void showUnreadMailFailed(String message) {
        showToast(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void toDetailQBacaBook(QBacaBook book) {
        DetailBookActivity.start(getActivity(), book);
    }

    @Override
    public void toDetailProduct(Product product) {
        WebViewActivity.start(getActivity(), product.getUrl(), product.getName());
    }

    @Override
    public void onDestroy() {
        unregisterEventBusSubscriber(this);
        presenter.cancelRequest();
        super.onDestroy();
    }

    @Subscribe
    public void onRefreshUnreadmailsEvent(RefreshUnreadMailsEvent e) {
        presenter.loadUnreadMail();
    }

}
