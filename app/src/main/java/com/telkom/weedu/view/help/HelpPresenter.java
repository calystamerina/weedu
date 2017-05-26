package com.telkom.weedu.view.help;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class HelpPresenter<V extends HelpView> extends BasePresenter<V> implements IHelpPresenter<V>{

    @Inject
    public HelpPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void openChatActivity() {
        getView().toChatActivity();
    }

    @Override
    public void openContactActivity() {
        getView().toContactActivity();
    }

    @Override
    public void openFaqActivity() {
        getView().toFaqActivity();
    }

    @Override
    public void openTncActivity() {
        getView().toTncActivity();
    }

    @Override
    public void openAboutActivity() {
        getView().toAboutActivity();
    }
}
