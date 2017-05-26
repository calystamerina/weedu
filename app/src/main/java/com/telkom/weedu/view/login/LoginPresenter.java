package com.telkom.weedu.view.login;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.request.LoginRequest;
import com.telkom.weedu.data.api.model.response.LoginResponse;

import javax.inject.Inject;

import io.smooch.core.User;

/**
 * Created by sidiqpermana on 3/29/17.
 */

public class LoginPresenter<V extends LoginView> extends BasePresenter<V> implements ILoginPresenter<V>
        , ApiCallback.OnLoginRequestCallback {

    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void openMainActivity() {
        getView().toMainActivity();
    }

    @Override
    public void openRegisterActivity() {
        getView().toRegisterActivity();
    }

    @Override
    public void onLoginRequestSuccess(LoginResponse loginResponse) {
        getView().hideLoading();
        getDataManager().saveUserPreference(loginResponse.getResult());
        setSmoochUser(loginResponse.getResult().getProfile());
        getView().toMainActivity();
    }

    @Override
    public void onLoginRequestFailed(String errorMessage) {
        getView().hideLoading();
        getView().showToast(errorMessage);
    }


    @Override
    public void postLoginRequest(LoginRequest loginRequest) {
        getDataManager().login(loginRequest, this);
        getView().showLoading();
    }

    private void setSmoochUser(Profile profile) {
        User.getCurrentUser().setFirstName(profile.getFirstName());
        User.getCurrentUser().setLastName(profile.getLastName());
        User.getCurrentUser().setEmail(profile.getEdumail());
    }
}
