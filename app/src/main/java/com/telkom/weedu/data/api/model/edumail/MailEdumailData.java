package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class MailEdumailData implements Parcelable {
    @SerializedName("end")
    private int end;
    @SerializedName("q")
    private String q;
    @SerializedName("result")
    private ArrayList<ResultItem> listResultItems = new ArrayList<>();
    @SerializedName("title")
    private String title;
    @SerializedName("totaldrafts")
    private int totalDrafts;
    @SerializedName("totalunread")
    private int totalUnread;

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public ArrayList<ResultItem> getListResultItems() {
        return listResultItems;
    }

    public void setListResultItems(ArrayList<ResultItem> listResultItems) {
        this.listResultItems = listResultItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.end);
        dest.writeString(this.q);
        dest.writeTypedList(this.listResultItems);
        dest.writeString(this.title);
        dest.writeInt(this.totalDrafts);
        dest.writeInt(this.totalUnread);
    }

    public MailEdumailData() {
    }

    protected MailEdumailData(Parcel in) {
        this.end = in.readInt();
        this.q = in.readString();
        this.listResultItems = in.createTypedArrayList(ResultItem.CREATOR);
        this.title = in.readString();
        this.totalDrafts = in.readInt();
        this.totalUnread = in.readInt();
    }

    public static final Parcelable.Creator<MailEdumailData> CREATOR = new Parcelable.Creator<MailEdumailData>() {
        @Override
        public MailEdumailData createFromParcel(Parcel source) {
            return new MailEdumailData(source);
        }

        @Override
        public MailEdumailData[] newArray(int size) {
            return new MailEdumailData[size];
        }
    };
}
