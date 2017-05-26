package com.telkom.weedu.data.api.model;

// Sample pojo model

import com.google.gson.annotations.SerializedName;

public class User {
    /**
     * userId : 29
     * profile : {"firstName":"Mochamad","lastName":"Gani Amri","birthday":"2017-02-17T00:00:00.000Z","phoneNo":"081296458087"}
     * accessToken : oDe9XodofKJ1ORdWR0PNBPHuhAi3Xiutvh7rFy1oB5WtRxDJ0mtj92FZXdGC6Yu8
     * edumailToken : qrtulKyUgDDLM2Y5RCkqNE9GAGQ6MTQ5MTQwNzAyNDMwO
     */

    @SerializedName("userId")
    private int userId;
    @SerializedName("profile")
    private Profile profile;
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("edumailToken")
    private String edumailToken;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEdumailToken() {
        return edumailToken;
    }

    public void setEdumailToken(String edumailToken) {
        this.edumailToken = edumailToken;
    }
}