package com.telkom.weedu.view.faq;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;


public class FaqPresenter<V extends FaqView> extends BasePresenter<V> implements IFaqPresenter<V> {

    @Inject
    public FaqPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
