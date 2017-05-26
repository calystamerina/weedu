package com.telkom.weedu.view.emailconfirmation;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.request.RegisterRequest;
import com.telkom.weedu.data.api.model.response.RegisterResponse;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 3/30/17.
 */

public class EmailConfirmationPresenter<V extends EmailConfirmationView> extends BasePresenter<V> implements IEmailConfirmationPresenter<V>, ApiCallback.OnRegisterRequestCallback {
    @Inject
    public EmailConfirmationPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void openMainActivity() {
        getView().toMainActivity();
    }

    @Override
    public void postRegister(RegisterRequest request) {
        getView().showLoading();
        getDataManager().register(request, this);
    }

    @Override
    public void onRegisterRequestSuccess(RegisterResponse registerResponse) {
        getView().hideLoading();
        getView().showToast("You have successfully registered");
        getView().toLoginActivity();
    }

    @Override
    public void onRegisterRequestFailed(String message) {
        getView().hideLoading();
        getView().showAlert(message);
    }
}
