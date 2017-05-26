package com.telkom.weedu.data.api.model.response;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.data.api.model.Register;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class RegisterResponse {

    /**
     * status : 200
     * message : {"realm":"User","username":"ghiyats","email":"ghiyats@nusantarabetastudio.com","emailVerified":false,"id":7,"credentials":{}}
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private Register message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Register getMessage() {
        return message;
    }

    public void setMessage(Register message) {
        this.message = message;
    }
}
