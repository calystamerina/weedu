package com.telkom.weedu.view.intro;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 4/5/17.
 */

public class IntroItemPresenter<V extends IntroItemView> extends BasePresenter<V> implements IIntroItemPresenter<V> {
    @Inject
    public IntroItemPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
