package com.telkom.weedu.view.myaccount;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.Profile;

public interface MyAccountView extends IBaseView {

    void toEditProfileActivity();

    void toResetPasswordActivity();

    void toTopupActivity();

    void toRedeemActivity();

    void logoutAccount();

    void setProfile(Profile profile);

    void showProfilePicture(String profilePicture);
}
