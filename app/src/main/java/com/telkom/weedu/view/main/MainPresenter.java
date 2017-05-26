package com.telkom.weedu.view.main;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;


/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class MainPresenter <V extends MainView>  extends BasePresenter<V> implements IMainPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
