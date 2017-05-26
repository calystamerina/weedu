package com.telkom.weedu.data.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghiyatshanif on 4/27/17.
 */

public class Register {
    /**
     * realm : User
     * username : ghiyats
     * email : ghiyats@nusantarabetastudio.com
     * emailVerified : false
     * id : 7
     * credentials : {}
     */

    @SerializedName("realm")
    private String realm;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("emailVerified")
    private boolean emailVerified;
    @SerializedName("id")
    private int id;
    @SerializedName("credentials")
    private CredentialsBean credentials;

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CredentialsBean getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsBean credentials) {
        this.credentials = credentials;
    }

    public static class CredentialsBean {
    }

}
