package com.telkom.weedu.view.intro;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;

/**
 * Created by sidiqpermana on 4/5/17.
 */

public interface IIntroductionPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void openLoginActivity();
}
