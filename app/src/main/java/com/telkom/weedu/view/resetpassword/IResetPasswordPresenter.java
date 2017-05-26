package com.telkom.weedu.view.resetpassword;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;

public interface IResetPasswordPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void resetPassword(String oldPassword, String newPassword);

}
