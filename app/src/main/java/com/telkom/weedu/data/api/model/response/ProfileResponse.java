package com.telkom.weedu.data.api.model.response;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.data.api.model.Profile;

/**
 * Created by ghiyatshanif on 4/29/17.
 */

public class ProfileResponse {
    @SerializedName("profile")
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
