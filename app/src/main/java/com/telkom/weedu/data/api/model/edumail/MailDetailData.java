package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class MailDetailData implements Parcelable {
    @SerializedName("result")
    private ResultItem resultItem;
    @SerializedName("totaldrafts")
    private int totalDrafts;
    @SerializedName("totalunread")
    private int totalUnread;

    public int getTotalDrafts() {
        return totalDrafts;
    }

    public void setTotalDrafts(int totalDrafts) {
        this.totalDrafts = totalDrafts;
    }

    public int getTotalUnread() {
        return totalUnread;
    }

    public void setTotalUnread(int totalUnread) {
        this.totalUnread = totalUnread;
    }

    public ResultItem getResultItem() {
        return resultItem;
    }

    public void setResultItem(ResultItem resultItem) {
        this.resultItem = resultItem;
    }

    public MailDetailData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.resultItem, flags);
        dest.writeInt(this.totalDrafts);
        dest.writeInt(this.totalUnread);
    }

    protected MailDetailData(Parcel in) {
        this.resultItem = in.readParcelable(ResultItem.class.getClassLoader());
        this.totalDrafts = in.readInt();
        this.totalUnread = in.readInt();
    }

    public static final Creator<MailDetailData> CREATOR = new Creator<MailDetailData>() {
        @Override
        public MailDetailData createFromParcel(Parcel source) {
            return new MailDetailData(source);
        }

        @Override
        public MailDetailData[] newArray(int size) {
            return new MailDetailData[size];
        }
    };
}
