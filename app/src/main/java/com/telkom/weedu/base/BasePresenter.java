package com.telkom.weedu.base;

import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.model.ApiError;

import javax.inject.Inject;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private final DataManager mDataManager;
    private V mView;

    @Inject
    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        //mView = null;
        //getDataManager().cancelAllRequest();
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(IBaseView) before" +
                    " requesting data to the Presenter");
        }
    }

    @Override
    public void handleApiError(ApiError error) {

    }
}