package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class Target implements Parcelable {
    @SerializedName("Relays")
    private ArrayList<String> relays = new ArrayList<>();
    @SerializedName("Mailbox")
    private String mailbox;
    @SerializedName("Domain")
    private String domain;
    @SerializedName("Params")
    private String params;

    public ArrayList<String> getRelays() {
        return relays;
    }

    public void setRelays(ArrayList<String> relays) {
        this.relays = relays;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.relays);
        dest.writeString(this.mailbox);
        dest.writeString(this.domain);
        dest.writeString(this.params);
    }

    public Target() {
    }

    protected Target(Parcel in) {
        this.relays = in.createStringArrayList();
        this.mailbox = in.readString();
        this.domain = in.readString();
        this.params = in.readString();
    }

    public static final Parcelable.Creator<Target> CREATOR = new Parcelable.Creator<Target>() {
        @Override
        public Target createFromParcel(Parcel source) {
            return new Target(source);
        }

        @Override
        public Target[] newArray(int size) {
            return new Target[size];
        }
    };
}
