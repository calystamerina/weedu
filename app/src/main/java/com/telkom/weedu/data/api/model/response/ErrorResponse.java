package com.telkom.weedu.data.api.model.response;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.data.api.model.ApiError;

/**
 * Created by ghiyatshanif on 4/10/17.
 */

public class ErrorResponse {
    @SerializedName("error")
    private ApiError error;

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error=" + error +
                '}';
    }

}
