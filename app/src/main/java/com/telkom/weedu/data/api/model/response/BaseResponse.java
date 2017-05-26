package com.telkom.weedu.data.api.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghiyatshanif on 4/27/17.
 */

public class BaseResponse {
    /**
     * status : 200
     * message : Success
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
