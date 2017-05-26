package com.telkom.weedu.view.dashboard;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.User;
import com.telkom.weedu.data.api.model.response.HistoryResponse;

import javax.inject.Inject;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class DashboardPresenter<V extends DashboardView> extends BasePresenter<V> implements IDashboardPresenter<V>, ApiCallback.OnHistoryRequestCallback {

    @Inject
    public DashboardPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void loadRecomendation() {
        getView().showLoading();
        User user = getDataManager().getUserPreference();
        getDataManager().getHistory(String.valueOf(user.getUserId()), user.getAccessToken(), this);
    }

    @Override
    public void onHistoryRequestSuccess(HistoryResponse historyResponse) {
        getView().hideLoading();
        getView().showHistoryList(historyResponse.getHistories());
    }

    @Override
    public void onHistoryRequestFailed(String message) {
        getView().hideLoading();
        getView().showToast(message);
    }
}
