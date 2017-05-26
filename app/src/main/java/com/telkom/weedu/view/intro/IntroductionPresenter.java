package com.telkom.weedu.view.intro;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 4/5/17.
 */

public class IntroductionPresenter<V extends IntroductionView> extends BasePresenter<V> implements IIntroductionPresenter<V> {
    @Inject
    public IntroductionPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void openLoginActivity() {
        getView().toLoginActivity();
        getDataManager().setFirstTime(false);
    }

}
