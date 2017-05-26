package com.telkom.weedu.data.api.model.response.edumail;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.ReplyData;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class ReplyResponse extends BaseEdumailResponse {
    @SerializedName("data")
    private ReplyData replyData;

    public ReplyData getReplyData() {
        return replyData;
    }

    public void setReplyData(ReplyData replyData) {
        this.replyData = replyData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.replyData, flags);
    }

    public ReplyResponse() {
    }

    protected ReplyResponse(Parcel in) {
        super(in);
        this.replyData = in.readParcelable(ReplyData.class.getClassLoader());
    }

    public static final Creator<ReplyResponse> CREATOR = new Creator<ReplyResponse>() {
        @Override
        public ReplyResponse createFromParcel(Parcel source) {
            return new ReplyResponse(source);
        }

        @Override
        public ReplyResponse[] newArray(int size) {
            return new ReplyResponse[size];
        }
    };
}
