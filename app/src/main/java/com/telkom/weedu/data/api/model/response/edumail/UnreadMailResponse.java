package com.telkom.weedu.data.api.model.response.edumail;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.UnreadMail;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class UnreadMailResponse extends BaseEdumailResponse {
    @SerializedName("data")
    private UnreadMail data;

    public UnreadMail getData() {
        return data;
    }

    public void setData(UnreadMail data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.data, flags);
    }

    public UnreadMailResponse() {
    }

    protected UnreadMailResponse(Parcel in) {
        super(in);
        this.data = in.readParcelable(UnreadMail.class.getClassLoader());
    }

    public static final Creator<UnreadMailResponse> CREATOR = new Creator<UnreadMailResponse>() {
        @Override
        public UnreadMailResponse createFromParcel(Parcel source) {
            return new UnreadMailResponse(source);
        }

        @Override
        public UnreadMailResponse[] newArray(int size) {
            return new UnreadMailResponse[size];
        }
    };
}
