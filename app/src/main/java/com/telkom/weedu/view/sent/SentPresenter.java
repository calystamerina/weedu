package com.telkom.weedu.view.sent;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public class SentPresenter <V extends SentView> extends BasePresenter<V> implements ISentPresenter<V> {
    @Inject
    public SentPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
