package com.telkom.weedu.view.myaccount;

import android.util.Log;

import com.telkom.weedu.BuildConfig;
import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.edumail.UserDetail;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.api.model.response.BaseResponse;
import com.telkom.weedu.data.api.model.response.ProfileResponse;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class MyAccountPresenter<V extends MyAccountView> extends BasePresenter<V> implements IMyAccountPresenter<V>,
        ApiCallback.OnGetEdumailProfileCallback, ApiCallback.OnLoadProfileRequestCallback, ApiCallback.OnLogoutRequestCallback {

    @Inject
    public MyAccountPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void openEditProfileActivity() {
        getView().toEditProfileActivity();
    }

    @Override
    public void openResetPasswordActivity() {
        getView().toResetPasswordActivity();
    }

    @Override
    public void openTopupActivity() {
        getView().toTopupActivity();
    }

    @Override
    public void openRedeemActivity() {
        getView().toRedeemActivity();
    }

    @Override
    public void logout() {
        getView().showLoading();
        getDataManager().logout(getDataManager().getUserPreference().getAccessToken(), this);
    }

    @Override
    public void initializeAccount() {
        getView().setProfile(getDataManager().getUserPreference().getProfile());
    }

    @Override
    public void getUserProfile() {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setEdumailToken(getDataManager().getUserPreference().getEdumailToken());

        getDataManager().getProfile(mailRequest, this);
        getDataManager().loadProfile(String.valueOf(getDataManager().getUserPreference().getUserId())
                , getDataManager().getUserPreference().getAccessToken()
                , this);
    }

    @Override
    public void onGetProfileRequestSuccess(UserDetail userDetail) {
        String profileUrl = BuildConfig.EDUMAIL_URL + userDetail.getAvatar().replace(" ", "%20");
        Log.d("Profile", profileUrl);
        getView().showProfilePicture(profileUrl);
    }

    @Override
    public void onGetProfileRequestFailed(String message) {
        Timber.d("Profile", message);
    }

    @Override
    public void onLoadRequestSuccess(ProfileResponse response) {
        response.getProfile().setEdumail(getDataManager().getUserProfile().getEdumail());
        getView().setProfile(response.getProfile());
        getDataManager().updateProfile(response.getProfile());

    }

    @Override
    public void onLoadRequestFailed(String message) {
        getView().showToast("Failed to fetch user profile");
        initializeAccount();
    }

    @Override
    public void onLogoutRequestSuccess(BaseResponse response) {
        getDataManager().clearUserPreference();
        getView().hideLoading();
        getView().logoutAccount();
    }

    @Override
    public void onLogoutRequestFailed(String message) {
        getView().hideLoading();
        getView().showToast(message);
    }
}
