package com.telkom.weedu.view.about;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;


public class AboutPresenter<V extends AboutView> extends BasePresenter<V> implements IAboutPresenter<V> {

    @Inject
    public AboutPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
