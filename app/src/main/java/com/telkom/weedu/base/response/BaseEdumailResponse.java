package com.telkom.weedu.base.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class BaseEdumailResponse implements Parcelable {
    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.status);
    }

    public BaseEdumailResponse() {
    }

    protected BaseEdumailResponse(Parcel in) {
        this.code = in.readInt();
        this.status = in.readString();
    }

}
