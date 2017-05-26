package com.telkom.weedu.view.contactus;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.request.ContactUsRequest;
import com.telkom.weedu.data.api.model.response.BaseResponse;
import com.telkom.weedu.utils.AppMessages;

import javax.inject.Inject;


public class ContactUsPresenter<V extends ContactUsView> extends BasePresenter<V> implements IContactUsPresenter<V>, ApiCallback.OnPostContactUsCallback {

    @Inject
    public ContactUsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void postContactUs(ContactUsRequest request) {
        getView().showLoading();
        getDataManager().contactUs(String.valueOf(getDataManager().getUserPreference().getUserId())
                , getDataManager().getUserPreference().getAccessToken()
                , request, this);
    }

    @Override
    public void onPostContactUsSuccess(BaseResponse loginResponse) {
        getView().hideLoading();
        getView().showAlert(AppMessages.SEND_CONTACT_US_SUCCESS);
    }

    @Override
    public void onPostContactUsFailed(String message) {
        getView().hideLoading();
        getView().showToast(message);
    }
}
