package com.telkom.weedu.view.dashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseFragment;
import com.telkom.weedu.data.api.model.History;
import com.telkom.weedu.data.api.model.LastViewed;
import com.telkom.weedu.data.api.model.Progress;
import com.telkom.weedu.view.adapters.HistoryAdapter;
import com.telkom.weedu.view.adapters.LastViewedAdapter;
import com.telkom.weedu.view.adapters.ProgressAdapter;

import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements DashboardView {


    @BindView(R.id.rv_last_viewed)
    RecyclerView rvLastViewed;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindString(R.string.lorem_ipsum)
    String loremIpsum;
    @BindView(R.id.pb_indicator)
    ProgressBar pbIndicator;

    private ProgressAdapter mProgressAdapter;
    private LastViewedAdapter mLastViewedAdapter;
    private HistoryAdapter historyAdapter;

    private LinkedList<Progress> progressArrayList;
    private LinkedList<LastViewed> lastViewedArrayList;

    @Inject
    IDashboardPresenter<DashboardView> presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initLib() {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        mProgressAdapter = new ProgressAdapter(getContext(), progressArrayList);
        LinearLayoutManager progressLayoutManager = new LinearLayoutManager(getContext());
        rvHistory.setLayoutManager(progressLayoutManager);
        rvHistory.setNestedScrollingEnabled(false);
        rvHistory.setAdapter(mProgressAdapter);

        mLastViewedAdapter = new LastViewedAdapter(getContext(), lastViewedArrayList);
        LinearLayoutManager lastViewedLayoutManager = new LinearLayoutManager(getContext());
        rvLastViewed.setLayoutManager(lastViewedLayoutManager);
        rvLastViewed.setAdapter(mLastViewedAdapter);
        rvLastViewed.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initProcess() {
        presenter.loadRecomendation();
    }

    public static DashboardFragment newInstance() {

        Bundle args = new Bundle();

        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showHistoryList(LinkedList<History> histories) {
        historyAdapter = new HistoryAdapter(getContext(), histories);
        LinearLayoutManager historyLinearLayoutManager = new LinearLayoutManager(getContext());
        rvHistory.setLayoutManager(historyLinearLayoutManager);
        rvHistory.setNestedScrollingEnabled(true);
        rvHistory.setAdapter(historyAdapter);
    }

    @Override
    public void showLoading() {
        rvHistory.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        rvHistory.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
