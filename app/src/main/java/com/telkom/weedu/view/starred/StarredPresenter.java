package com.telkom.weedu.view.starred;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public class StarredPresenter <V extends StarredView> extends BasePresenter<V> implements IStarredPresenter<V> {
    @Inject
    public StarredPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
