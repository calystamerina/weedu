package com.telkom.weedu.view.about;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.telkom.weedu.BuildConfig;
import com.telkom.weedu.R;
import com.telkom.weedu.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;


public class AboutActivity extends BaseActivity implements AboutView {

    @Inject
    IAboutPresenter<AboutView> presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_application_name)
    TextView tvApplicationName;
    @BindView(R.id.tv_application_version)
    TextView tvApplicationVersion;

    public static void start(Context context) {
        Intent starter = new Intent(context, AboutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_about;
    }

    @Override
    protected void initLib() {
        // TODO implements injection here
        getActivityComponent().inject(AboutActivity.this);
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initUI() {
        setupToolbar(toolbar, getString(R.string.activity_title_about), true);
        tvApplicationName.setText(R.string.app_name);
        tvApplicationVersion.setText("version " + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void initAction() {

    }

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
