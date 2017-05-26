package com.telkom.weedu.data.api.model.response.edumail;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.ForwardData;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class ForwardResponse extends BaseEdumailResponse{
    @SerializedName("data")
    private ForwardData forwardData;

    public ForwardData getForwardData() {
        return forwardData;
    }

    public void setForwardData(ForwardData forwardData) {
        this.forwardData = forwardData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.forwardData, flags);
    }

    public ForwardResponse() {
    }

    protected ForwardResponse(Parcel in) {
        super(in);
        this.forwardData = in.readParcelable(ForwardData.class.getClassLoader());
    }

    public static final Creator<ForwardResponse> CREATOR = new Creator<ForwardResponse>() {
        @Override
        public ForwardResponse createFromParcel(Parcel source) {
            return new ForwardResponse(source);
        }

        @Override
        public ForwardResponse[] newArray(int size) {
            return new ForwardResponse[size];
        }
    };
}
