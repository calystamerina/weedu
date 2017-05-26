package com.telkom.weedu.view.register;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.request.RegisterRequest;

/**
 * Created by sidiqpermana on 3/29/17.
 */

public interface RegisterView extends IBaseView {
    void toLoginActivity();
    void toMainActivity();
    void toEmailConfirmationActivity(RegisterRequest request);
}
