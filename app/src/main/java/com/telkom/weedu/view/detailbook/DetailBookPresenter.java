package com.telkom.weedu.view.detailbook;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by Dodi Rivaldi on 06/04/2017.
 */

public class DetailBookPresenter<V extends DetailBookView> extends BasePresenter<V> implements IDetailBookPresenter<V> {
    @Inject
    public DetailBookPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
