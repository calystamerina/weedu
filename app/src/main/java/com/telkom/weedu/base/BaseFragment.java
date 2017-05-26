package com.telkom.weedu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telkom.weedu.di.components.ActivityComponent;
import com.telkom.weedu.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements IBaseView {

    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUnBinder(ButterKnife.bind(this, view));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onViewReady();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void setupToolbar(Toolbar toolbar, String title, boolean isChild) {
        mActivity.setupToolbar(toolbar, title, isChild);
    }

    @Override
    public void setupToolbar(String title, boolean isChild) {
        mActivity.setupToolbar(title, isChild);
    }

    @Override
    public void showAlert(String message) {
        mActivity.showAlert(message);
    }

    @Override
    public void showToast(String message) {
        mActivity.showToast(message);
    }

    @Override
    public void showSnackbar(String message) {
        mActivity.showSnackbar(message);
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public boolean isNetworkAvailable() {
        return mActivity.isNetworkAvailable();
    }

    @Override
    public void onError(int resourceString) {
        onError(getString(resourceString));
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public ActivityComponent getActivityComponent() {
        return mActivity.getActivityComponent();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    private void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void finishActivity() {
        mActivity.finishActivity();
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


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    public void postActivityEvent(BaseEvent e) {
        EventBus.getDefault().post(e);
    }

    public void registerEventBusSubscriber(BaseFragment baseFragment) {
        EventBus.getDefault().register(baseFragment);
    }

    public void unregisterEventBusSubscriber(BaseFragment baseFragment) {
        EventBus.getDefault().unregister(baseFragment);
    }
}