package com.telkom.weedu.view.register;

import android.os.Handler;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.model.request.RegisterRequest;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 3/29/17.
 */

public class RegisterPresenter<V extends RegisterView> extends BasePresenter<V> implements IRegisterPresenter<V> {
    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void openLoginActivity() {
        getView().toLoginActivity();
    }

    @Override
    public void openEmailConfirmationActivity(RegisterRequest request) {
        getView().toEmailConfirmationActivity(request);
    }

    @Override
    public void registerRequest(final RegisterRequest request) {
        getView().showLoading();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                getView().hideLoading();
                getView().toEmailConfirmationActivity(request);
            }
        };

//        wait 3 seconds
        handler.postDelayed(r, 1000);
    }
}
