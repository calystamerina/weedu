package com.telkom.weedu.view.contactus;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.request.ContactUsRequest;

public interface IContactUsPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void postContactUs(ContactUsRequest request);
}
