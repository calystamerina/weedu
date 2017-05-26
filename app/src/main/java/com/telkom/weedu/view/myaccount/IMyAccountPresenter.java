package com.telkom.weedu.view.myaccount;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public interface IMyAccountPresenter <V extends IBaseView> extends IBasePresenter<V> {

    void openEditProfileActivity();
    void openResetPasswordActivity();
    void openTopupActivity();
    void openRedeemActivity();
    void logout();
    void initializeAccount();
    void getUserProfile();
}
