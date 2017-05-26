package com.telkom.weedu.view.register;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.request.RegisterRequest;

/**
 * Created by sidiqpermana on 3/29/17.
 */

public interface IRegisterPresenter<V extends IBaseView> extends IBasePresenter<V> {

    void openLoginActivity();

    void openEmailConfirmationActivity(RegisterRequest request);

    void registerRequest(RegisterRequest request);
}
