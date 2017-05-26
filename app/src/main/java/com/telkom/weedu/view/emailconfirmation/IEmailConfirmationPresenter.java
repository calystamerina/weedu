package com.telkom.weedu.view.emailconfirmation;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.request.RegisterRequest;

/**
 * Created by sidiqpermana on 3/30/17.
 */

public interface IEmailConfirmationPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void openMainActivity();
    void postRegister(RegisterRequest request);
}
