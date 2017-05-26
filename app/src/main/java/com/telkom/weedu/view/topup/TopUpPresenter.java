package com.telkom.weedu.view.topup;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;


public class TopUpPresenter<V extends TopUpView> extends BasePresenter<V> implements ITopUpPresenter<V> {

    @Inject
    public TopUpPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
