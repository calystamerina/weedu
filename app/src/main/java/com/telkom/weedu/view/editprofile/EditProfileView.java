package com.telkom.weedu.view.editprofile;


import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.Profile;

public interface EditProfileView extends IBaseView {

    void setCurrentProfile(Profile profile);

    void showEditProfileSuccessDialog(String message);

    void postUpdateEvent();
}
