package com.telkom.weedu.base;

import com.telkom.weedu.data.api.model.ApiError;

public interface IBasePresenter<T extends IBaseView> {

    void onAttach(T view);

    void onDetach();

    void handleApiError(ApiError error);

}