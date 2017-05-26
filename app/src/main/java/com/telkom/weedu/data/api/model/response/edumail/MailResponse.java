package com.telkom.weedu.data.api.model.response.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.MailEdumailData;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public class MailResponse extends BaseEdumailResponse implements Parcelable {
    @SerializedName("data")
    private MailEdumailData edumailData;

    public MailEdumailData getEdumailData() {
        return edumailData;
    }

    public void setEdumailData(MailEdumailData edumailData) {
        this.edumailData = edumailData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.edumailData, flags);
    }

    public MailResponse() {
    }

    protected MailResponse(Parcel in) {
        super(in);
        this.edumailData = in.readParcelable(MailEdumailData.class.getClassLoader());
    }

    public static final Creator<MailResponse> CREATOR = new Creator<MailResponse>() {
        @Override
        public MailResponse createFromParcel(Parcel source) {
            return new MailResponse(source);
        }

        @Override
        public MailResponse[] newArray(int size) {
            return new MailResponse[size];
        }
    };
}
