package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 5/1/17.
 */

public class SearchResultData implements Parcelable {
    @SerializedName("end")
    private int end;
    @SerializedName("result")
    private ArrayList<SearchResultItem> listResult;
    @SerializedName("totaldrafts")
    private int totaldrafts;
    @SerializedName("totalunread")
    private int totalUnread;

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ArrayList<SearchResultItem> getListResult() {
        return listResult;
    }

    public void setListResult(ArrayList<SearchResultItem> listResult) {
        this.listResult = listResult;
    }

    public int getTotaldrafts() {
        return totaldrafts;
    }

    public void setTotaldrafts(int totaldrafts) {
        this.totaldrafts = totaldrafts;
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
        dest.writeTypedList(this.listResult);
        dest.writeInt(this.totaldrafts);
        dest.writeInt(this.totalUnread);
    }

    public SearchResultData() {
    }

    protected SearchResultData(Parcel in) {
        this.end = in.readInt();
        this.listResult = in.createTypedArrayList(SearchResultItem.CREATOR);
        this.totaldrafts = in.readInt();
        this.totalUnread = in.readInt();
    }

    public static final Parcelable.Creator<SearchResultData> CREATOR = new Parcelable.Creator<SearchResultData>() {
        @Override
        public SearchResultData createFromParcel(Parcel source) {
            return new SearchResultData(source);
        }

        @Override
        public SearchResultData[] newArray(int size) {
            return new SearchResultData[size];
        }
    };
}
