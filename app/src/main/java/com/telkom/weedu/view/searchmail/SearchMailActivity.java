package com.telkom.weedu.view.searchmail;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.event.AddRemoveFavoriteMailEvent;
import com.telkom.weedu.event.RefreshMailboxEvent;
import com.telkom.weedu.event.RefreshMailboxItemEvent;
import com.telkom.weedu.event.RemoveMailEvent;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.view.adapters.InboxAdapter;
import com.telkom.weedu.view.maildetail.MailDetailActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchMailActivity extends BaseActivity implements SearchMailView, SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.tv_error_message)
    TextView tvErrorMessage;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    @BindView(R.id.swipe_refresh_layout_search)
    SwipeRefreshLayout swipeRefreshLayoutSearch;
    @BindView(R.id.tv_keyword)
    TextView tvKeyword;

    @Inject
    ISearchMailPresenter<SearchMailView> presenter;

    private InboxAdapter adapter;
    private String keyword;

    @Override
    public void showInitialLoading() {
        if (swipeRefreshLayoutSearch != null){
            swipeRefreshLayoutSearch.setRefreshing(true);
        }
    }

    @Override
    public void hideInitialLoading() {
        if (swipeRefreshLayoutSearch != null){
            swipeRefreshLayoutSearch.setRefreshing(false);
        }
    }

    @Override
    public void toMailDetailActivity(Mail mail) {
        MailDetailActivity.start(SearchMailActivity.this, mail.getType(), mail);
    }

    @Override
    public void showSearchResultList(LinkedList<Mail> list) {
        adapter.add(list);
    }

    @Override
    public void showEmptyResult(String message) {
        tvErrorMessage.setVisibility(View.VISIBLE);
        tvErrorMessage.setText(message);
    }

    @Override
    public void showErrorResult(String message) {
        tvErrorMessage.setVisibility(View.VISIBLE);
        tvErrorMessage.setText(message);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_search_mail;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        registerEventBusSubscriber(SearchMailActivity.this);
    }

    @Override
    protected void initIntent() {
        keyword = getIntent().getStringExtra(BundleKeys.KEY_KEYWORD);
    }

    @Override
    protected void initUI() {
        setupToolbar(getString(R.string.activity_title_searchmail), true);

        setUpSwipeRefreshLayout();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        rvSearch.setHasFixedSize(true);
        rvSearch.setLayoutManager(new LinearLayoutManager(SearchMailActivity.this));
        rvSearch.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new InboxAdapter(SearchMailActivity.this, new LinkedList<Mail>());
        rvSearch.setAdapter(adapter);
    }

    private void setUpSwipeRefreshLayout() {
        swipeRefreshLayoutSearch.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.amber600),
                ContextCompat.getColor(this, R.color.red600),
                ContextCompat.getColor(this, R.color.colorAccent));
        swipeRefreshLayoutSearch.setOnRefreshListener(this);
    }

    @Override
    protected void initAction() {
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.openMailDetailActivity(adapter.getDatas().get(position));
            }
        });
    }

    @Override
    protected void initProcess() {
        String resultKey = "Hasil pencarian untuk <i><b>"+keyword+"</b></i>";
        tvKeyword.setText(Html.fromHtml(resultKey));
        if (tvErrorMessage.getVisibility() == View.VISIBLE){
            tvErrorMessage.setVisibility(View.GONE);
        }

        presenter.loadMail(keyword);
    }

    public static void start(Context context, String keyword) {
        Intent starter = new Intent(context, SearchMailActivity.class);
        starter.putExtra(BundleKeys.KEY_KEYWORD, keyword);
        context.startActivity(starter);
    }

    @Override
    public void onRefresh() {
        if (!swipeRefreshLayoutSearch.isRefreshing()){
            adapter.getDatas().clear();
            adapter.notifyDataSetChanged();
            rvSearch.setVisibility(View.GONE);

            presenter.loadMail(keyword);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        unregisterEventBusSubscriber(SearchMailActivity.this);
        super.onDestroy();
    }

    @Subscribe
    public void onRefreshMailboxItem(RefreshMailboxItemEvent e){
        if (adapter.getDatas() != null){
            Mail updatedMail = null;
            for (Mail mail : adapter.getDatas()){
                if (mail.getMailId().equalsIgnoreCase(e.getMailId())){
                    updatedMail = mail;
                    break;
                }
            }

            if (updatedMail != null){
                if (updatedMail.isUnread()){
                    updatedMail.setUnread(false);
                    adapter.addOrUpdate(updatedMail);
                }
            }
        }
    }

    @Subscribe
    public void onRemoveMailboxItem(RemoveMailEvent e){
        removeSelectedMailFromList(e.getMailId());
    }

    private void removeSelectedMailFromList(String mailId) {
        Mail selectedMail = null;
        for (Mail mail : adapter.getDatas()){
            if (mail.getMailId().equalsIgnoreCase(mailId)){
                selectedMail = mail;
                break;
            }
        }

        if (selectedMail != null){
            adapter.remove(selectedMail);
        }
    }

    @Subscribe
    public void onStarChangesMailboxItem(AddRemoveFavoriteMailEvent e){
        if (adapter.getDatas() != null){
            Mail updatedMail = null;
            for (Mail mail : adapter.getDatas()){
                if (mail.getMailId().equalsIgnoreCase(e.getMail().getMailId())){
                    updatedMail = mail;
                    break;
                }
            }

            if (updatedMail != null){
                updatedMail.setStarred(e.getMail().isStarred());
                adapter.addOrUpdate(updatedMail);
            }
        }
    }

    @Subscribe
    public void onRefreshMailboxEvent(RefreshMailboxEvent e){
        initProcess();
    }
}
