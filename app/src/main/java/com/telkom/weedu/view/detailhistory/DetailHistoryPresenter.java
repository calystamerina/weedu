package com.telkom.weedu.view.detailhistory;


import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

public class DetailHistoryPresenter<V extends DetailHistoryView> extends BasePresenter<V> implements IDetailHistoryPresenter<V> {

    @Inject
    public DetailHistoryPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
