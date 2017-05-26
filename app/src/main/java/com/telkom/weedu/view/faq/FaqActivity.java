package com.telkom.weedu.view.faq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.base.adapter.BaseRecyclerAdapter;
import com.telkom.weedu.data.api.model.Faq;
import com.telkom.weedu.view.adapters.FaqAdapter;

import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.BindView;

import static com.telkom.weedu.R.string.activity_title_faq;


public class FaqActivity extends BaseActivity implements FaqView, BaseRecyclerAdapter.OnItemClickListener {

    @Inject
    IFaqPresenter<FaqView> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_faq)
    RecyclerView rvFaq;

    FaqAdapter faqAdapter;
    LinkedList<Faq> faqArrayList;

    public static void start(Context context) {
        Intent starter = new Intent(context, FaqActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_faq;
    }

    @Override
    protected void initLib() {
        // TODO implements injection here
        getActivityComponent().inject(FaqActivity.this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, getString(activity_title_faq), true);
        setupDummyFaq();
        faqAdapter = new FaqAdapter(this, faqArrayList);
        faqAdapter.setOnItemClickListener(this);
        rvFaq.setLayoutManager(new LinearLayoutManager(this));
        rvFaq.setAdapter(faqAdapter);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    private void setupDummyFaq() {
        faqArrayList = new LinkedList<>();
        faqArrayList.add(new Faq());
        faqArrayList.add(new Faq());
        faqArrayList.add(new Faq());
        faqArrayList.add(new Faq());
        faqArrayList.add(new Faq());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
