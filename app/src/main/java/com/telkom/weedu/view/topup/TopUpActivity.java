package com.telkom.weedu.view.topup;

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
import com.telkom.weedu.data.api.model.TopupStep;
import com.telkom.weedu.view.adapters.TopUpAdapter;

import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.BindView;


public class TopUpActivity extends BaseActivity implements TopUpView, BaseRecyclerAdapter.OnItemClickListener {

    @Inject
    ITopUpPresenter<TopUpView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_how_to_top_up)
    RecyclerView rvHowToTopUp;

    private TopUpAdapter topUpAdapter;
    private LinkedList<TopupStep> topupStepArrayList;


    public static void start(Context context) {
        Intent starter = new Intent(context, TopUpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_top_up;
    }

    @Override
    protected void initLib() {
        // TODO implements injection here
        getActivityComponent().inject(TopUpActivity.this);
        presenter.onAttach(this);
        setupDummyTopup();
        topUpAdapter = new TopUpAdapter(this, topupStepArrayList);
        rvHowToTopUp.setLayoutManager(new LinearLayoutManager(this));
        rvHowToTopUp.setAdapter(topUpAdapter);

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, getString(R.string.activity_title_topup), true);
    }

    @Override
    protected void initAction() {
        topUpAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void initProcess() {

    }

    private void setupDummyTopup() {
        topupStepArrayList = new LinkedList<>();
        topupStepArrayList.add(new TopupStep("Credit Card"));
        topupStepArrayList.add(new TopupStep("ATM Transfer"));
        topupStepArrayList.add(new TopupStep("T-Money"));
        topupStepArrayList.add(new TopupStep("BNI"));
        topupStepArrayList.add(new TopupStep("Alfamart"));
    }

    @Override
    public void onItemClick(View view, int position) {

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
