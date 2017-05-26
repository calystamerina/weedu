package com.telkom.weedu.data.api.model.response;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.data.api.model.User;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class LoginResponse {

    /**
     * status : 200
     * result : {"userId":29,"profile":{"firstName":"Mochamad","lastName":"Gani Amri","birthday":"2017-02-17T00:00:00.000Z","phoneNo":"081296458087"},"accessToken":"oDe9XodofKJ1ORdWR0PNBPHuhAi3Xiutvh7rFy1oB5WtRxDJ0mtj92FZXdGC6Yu8","edumailToken":"qrtulKyUgDDLM2Y5RCkqNE9GAGQ6MTQ5MTQwNzAyNDMwO"}
     */

    @SerializedName("status")
    private int status;
    @SerializedName("result")
    private User result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }

}
