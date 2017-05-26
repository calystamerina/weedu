package com.telkom.weedu.data.api.model.response.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.MailDetailData;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class MailDetailResponse extends BaseEdumailResponse implements Parcelable {
    @SerializedName("data")
    private MailDetailData data;

    public MailDetailData getData() {
        return data;
    }

    public void setData(MailDetailData data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
    }

    public MailDetailResponse() {
    }

    protected MailDetailResponse(Parcel in) {
        this.data = in.readParcelable(MailDetailData.class.getClassLoader());
    }

    public static final Parcelable.Creator<MailDetailResponse> CREATOR = new Parcelable.Creator<MailDetailResponse>() {
        @Override
        public MailDetailResponse createFromParcel(Parcel source) {
            return new MailDetailResponse(source);
        }

        @Override
        public MailDetailResponse[] newArray(int size) {
            return new MailDetailResponse[size];
        }
    };
}
