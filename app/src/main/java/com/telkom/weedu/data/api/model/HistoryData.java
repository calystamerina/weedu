package com.telkom.weedu.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghiyatshanif on 4/24/17.
 */

public class HistoryData implements Parcelable {
    /**
     * image : http://helpdeskdirect.net/wp-content/uploads/2016/07/Top_4_Trending_Programming_Languages_One_Must_Learn.jpg
     * start : 2017-04-23T10:00:00.000Z
     */

    @SerializedName("image")
    private String image;
    @SerializedName("start")
    private String start;
    @SerializedName("description")
    private String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.start);
        dest.writeString(this.description);
    }

    public HistoryData() {
    }

    protected HistoryData(Parcel in) {
        this.image = in.readString();
        this.start = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<HistoryData> CREATOR = new Parcelable.Creator<HistoryData>() {
        @Override
        public HistoryData createFromParcel(Parcel source) {
            return new HistoryData(source);
        }

        @Override
        public HistoryData[] newArray(int size) {
            return new HistoryData[size];
        }
    };
}
