package com.telkom.weedu.view.mail;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public class MailPresenter<V extends MailView> extends BasePresenter<V> implements IMailPresenter<V> {
    @Inject
    public MailPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
