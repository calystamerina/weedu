package com.telkom.weedu.view.editprofile;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.User;
import com.telkom.weedu.data.api.model.response.BaseResponse;

import javax.inject.Inject;


public class EditProfilePresenter<V extends EditProfileView> extends BasePresenter<V> implements IEditProfilePresenter<V>, ApiCallback.OnEditProfileRequestCallback {

    @Inject
    public EditProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void onEditRequestSuccess(BaseResponse response) {
        getView().hideLoading();
        getView().showToast("edit profile berhasil");
        getView().postUpdateEvent();
        getView().finishActivity();
    }

    @Override
    public void onEditRequestFailed(String message) {
        getView().hideLoading();
        getView().showToast("Edit Profile gagal");
    }

    @Override
    public void setupProfile() {
        getView().setCurrentProfile(getDataManager().getUserPreference().getProfile());
    }

    @Override
    public void editProfile(Profile profile) {
        getView().showLoading();
        User preference = getDataManager().getUserPreference();
        getDataManager().editProfile(profile, String.valueOf(preference.getUserId()), preference.getAccessToken(), this);
    }
}
