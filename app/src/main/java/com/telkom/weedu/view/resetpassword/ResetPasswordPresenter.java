package com.telkom.weedu.view.resetpassword;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.User;
import com.telkom.weedu.data.api.model.request.ResetPasswordRequest;
import com.telkom.weedu.data.api.model.response.BaseResponse;

import javax.inject.Inject;


public class ResetPasswordPresenter<V extends ResetPasswordView> extends BasePresenter<V> implements IResetPasswordPresenter<V>, ApiCallback.OnResetPasswordCallback {

    @Inject
    public ResetPasswordPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void resetPassword(String oldPassword, String newPassword) {
        getView().showLoading();
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(oldPassword, newPassword);
        User user = getDataManager().getUserPreference();
        getDataManager().resetPassword(resetPasswordRequest, String.valueOf(user.getUserId()), user.getAccessToken(), this);
    }

    @Override
    public void onResetPasswordSuccess(BaseResponse historyResponse) {
        getView().hideLoading();
        getDataManager().clearUserPreference();
        getView().showResetPasswordSuccessDialog();
    }

    @Override
    public void onResetPasswordFailed(String message) {
        getView().hideLoading();
        getView().showToast(message);
    }
}
