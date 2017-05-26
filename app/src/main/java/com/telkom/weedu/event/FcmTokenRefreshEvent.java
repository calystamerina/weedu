package com.telkom.weedu.event;

/**
 * Created by ghiyatshanif on 4/29/17.
 */

public class FcmTokenRefreshEvent {
    private String token;
    public FcmTokenRefreshEvent(String refreshedToken) {
        this.token = refreshedToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
