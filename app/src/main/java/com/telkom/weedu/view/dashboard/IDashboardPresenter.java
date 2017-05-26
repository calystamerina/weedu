package com.telkom.weedu.view.dashboard;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public interface IDashboardPresenter <V extends IBaseView> extends IBasePresenter<V> {
    void loadRecomendation();

}
