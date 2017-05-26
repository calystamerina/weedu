package com.telkom.weedu.view.login;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.request.LoginRequest;

/**
 * Created by sidiqpermana on 3/29/17.
 */

public interface ILoginPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void postLoginRequest(LoginRequest loginRequest);
    void openMainActivity();

    void openRegisterActivity();
}
