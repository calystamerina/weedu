
package com.telkom.weedu.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.telkom.weedu.R;
import com.telkom.weedu.di.components.ActivityComponent;
import com.telkom.weedu.di.components.DaggerActivityComponent;
import com.telkom.weedu.di.modules.ActivityModule;
import com.telkom.weedu.event.BaseEvent;
import com.telkom.weedu.utils.MessageFactory;
import com.telkom.weedu.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity
        implements IBaseView, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((BaseApplication) getApplication()).getComponent())
                .activityModule(new ActivityModule(this))
                .build();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(getLayoutResource());
        setUnbinder(ButterKnife.bind(this));

        onViewReady();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void setupToolbar(Toolbar toolbar, String title, boolean isChild) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isChild);
        }
    }

    @Override
    public void setupToolbar(String title, boolean isChild) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isChild);
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = MessageFactory.showLoadingDialog(this, getString(R.string.please_wait));
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackbar(message);
        } else {
            showSnackbar(getString(R.string.error));
        }
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void onError(int resourceString) {
        onError(getString(resourceString));
    }

    @Override
    public void showSnackbar(String message) {
        MessageFactory.showSnackbarMessage(findViewById(android.R.id.content), message);
    }

    @Override
    public void showToast(String message) {
        MessageFactory.showToast(message);
    }

    @Override
    public void showAlert(String message) {
        MessageFactory.showAlertDialog(this, message);
    }

    @Override
    public boolean isNetworkAvailable() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    private void setUnbinder(Unbinder unbinder) {
        this.mUnBinder = unbinder;
    }

    private void onViewReady() {
        initLib();
        initIntent();
        initUI();
        initAction();
        initProcess();
    }

    //    pass Layout here
    protected abstract int getLayoutResource();

    //    Init Presenter and Component Injection here
    protected abstract void initLib();

    //    Extract desired intent here
    protected abstract void initIntent();

    //    initialize the UI, setup toolbar, setText etc here
    protected abstract void initUI();

    //    initialize UI interaction here
    protected abstract void initAction();

    //    initialize main Process here e.g call presenter to load data
    protected abstract void initProcess();

    public void postActivityEvent(BaseEvent e) {
        EventBus.getDefault().post(e);
    }

    public void registerEventBusSubscriber(BaseActivity activity) {
        EventBus.getDefault().register(activity);
    }

    public void unregisterEventBusSubscriber(BaseActivity activity) {
        EventBus.getDefault().unregister(activity);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
}