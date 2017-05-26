package com.telkom.weedu.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghiyatshanif on 4/24/17.
 */

public class History implements Parcelable {

    /**
     * contentId : contentId123456
     * contentTitle : Parsing JSON di Android
     * createdAt : 2017-04-24T16:31:04.000Z
     * data : {"image":"http://helpdeskdirect.net/wp-content/uploads/2016/07/Top_4_Trending_Programming_Languages_One_Must_Learn.jpg","start":"2017-04-23T10:00:00.000Z"}
     * id : 44b44c4b-a72e-48de-960c-05537407b3a0
     * productId : fdba3e8e-1a16-40a7-be4e-15ca899cf6d4
     * productName : Ruangvideo
     * status : Complete
     * userId : 2
     */

    @SerializedName("contentId")
    private String contentId;
    @SerializedName("contentTitle")
    private String contentTitle;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("data")
    private HistoryData data;
    @SerializedName("id")
    private String id;
    @SerializedName("productId")
    private String productId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("status")
    private String status;
    @SerializedName("userId")
    private int userId;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public HistoryData getData() {
        return data;
    }

    public void setData(HistoryData data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.contentId);
        dest.writeString(this.contentTitle);
        dest.writeString(this.createdAt);
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.id);
        dest.writeString(this.productId);
        dest.writeString(this.productName);
        dest.writeString(this.status);
        dest.writeInt(this.userId);
    }

    public History() {
    }

    protected History(Parcel in) {
        this.contentId = in.readString();
        this.contentTitle = in.readString();
        this.createdAt = in.readString();
        this.data = in.readParcelable(HistoryData.class.getClassLoader());
        this.id = in.readString();
        this.productId = in.readString();
        this.productName = in.readString();
        this.status = in.readString();
        this.userId = in.readInt();
    }

    public static final Parcelable.Creator<History> CREATOR = new Parcelable.Creator<History>() {
        @Override
        public History createFromParcel(Parcel source) {
            return new History(source);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
