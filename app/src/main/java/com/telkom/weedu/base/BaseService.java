package com.telkom.weedu.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.telkom.weedu.di.components.DaggerServiceComponent;
import com.telkom.weedu.di.components.ServiceComponent;
import com.telkom.weedu.di.modules.ServiceModule;

/**
 * Created by sidiqpermana on 5/2/17.
 */

public abstract class BaseService extends Service implements IBaseView {

    private ServiceComponent serviceComponent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceComponent = DaggerServiceComponent.builder()
                .applicationComponent(((BaseApplication)getApplication()).getComponent())
                .serviceModule(new ServiceModule(this))
                .build();
    }

    public ServiceComponent getServiceComponent(){
        return serviceComponent;
    }

    @Override
    public void setupToolbar(Toolbar toolbar, String title, boolean isChild) {

    }

    @Override
    public void setupToolbar(String title, boolean isChild) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onError(int resourceString) {

    }

    @Override
    public void showAlert(String message) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackbar(String message) {

    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }

    @Override
    public void finishActivity() {

    }
}
