package com.telkom.weedu.view.detailhistory;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;
import com.telkom.weedu.data.api.model.History;
import com.telkom.weedu.utils.BundleKeys;
import com.telkom.weedu.utils.CommonUtils;
import com.telkom.weedu.utils.DateUtils;
import com.telkom.weedu.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailHistoryActivity extends BaseActivity implements DetailHistoryView {
    @Inject
    IDetailHistoryPresenter<DetailHistoryView> presenter;
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.htab_collapse_toolbar)
    CollapsingToolbarLayout htabCollapseToolbar;
    @BindView(R.id.htab_appbar)
    AppBarLayout htabAppbar;
    @BindView(R.id.tv_history_title)
    TextView tvHistoryTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_history_action)
    Button btnHistoryAction;
    @BindView(R.id.tv_history_description)
    TextView tvHistoryDescription;
    @BindView(R.id.htab_maincontent)
    CoordinatorLayout htabMaincontent;

    private History history;

    public static void start(Context context, History history) {
        Intent starter = new Intent(context, DetailHistoryActivity.class);
        starter.putExtra(BundleKeys.KEY_HISTORY, history);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail_history;
    }

    //    Init Presenter and Component Injection here
    @Override
    protected void initLib() {
        getActivityComponent().inject(DetailHistoryActivity.this);
        presenter.onAttach(this);

    }

    //    Extract desired intent here
    @Override
    protected void initIntent() {
        history = getIntent().getParcelableExtra(BundleKeys.KEY_HISTORY);
    }

    //    initialize the UI, setup toolbar, setText etc here
    @Override
    protected void initUI() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setupToolbar(toolbar, "", true);
        ImageUtils.setImageUrl(this, history.getData().getImage(), imgHistory);
        if (history.getData().getStart() != null) {
            tvTime.setText(CommonUtils.getReadableHour(history.getData().getStart()));
        } else {
            tvTime.setVisibility(View.GONE);
        }
        tvDate.setText(DateUtils.getLocalDayName(history.getCreatedAt()) + ", " + DateUtils.getReadableDate(history.getCreatedAt()));
        tvHistoryTitle.setText(history.getContentTitle());
        if (history.getData().getDescription() != null)
            tvHistoryDescription.setText(history.getData().getDescription());
    }

    //    initialize UI interaction here
    @Override
    protected void initAction() {

    }

    //    initialize main Process here e.g call presenter to load data
    @Override
    protected void initProcess() {

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
