package com.telkom.weedu.view.editprofile;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.Profile;

public interface IEditProfilePresenter<V extends IBaseView> extends IBasePresenter<V> {

    void setupProfile();

    void editProfile(Profile profile);

}
