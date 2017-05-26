package com.telkom.weedu.view.webview;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;

import javax.inject.Inject;


public class WebViewPresenter<V extends WebViewView> extends BasePresenter<V> implements IWebViewPresenter<V> {

    @Inject
    public WebViewPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
