package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class ResultItem implements Parcelable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Subject")
    private String subject;
    @SerializedName("Froms")
    private ArrayList<Target> listFromItem = new ArrayList<>();
    @SerializedName("To")
    private ArrayList<Target> listToItem = new ArrayList<>();
    @SerializedName("Body")
    private String body;
    @SerializedName("Date")
    private String date;
    @SerializedName("Attachments")
    private boolean attachments;
    @SerializedName("Starred")
    private boolean starred;
    @SerializedName("Trashed")
    private boolean trashed;
    @SerializedName("Deleted")
    private boolean deleted;
    @SerializedName("Unread")
    private boolean unread;
    @SerializedName("Count")
    private int count;
    @SerializedName("Type")
    private String type;
    @SerializedName("ConvMessages")
    private ArrayList<ConvMessageItem> listConvMessageItems = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<Target> getListFromItem() {
        return listFromItem;
    }

    public void setListFromItem(ArrayList<Target> listFromItem) {
        this.listFromItem = listFromItem;
    }

    public ArrayList<Target> getListToItem() {
        return listToItem;
    }

    public void setListToItem(ArrayList<Target> listToItem) {
        this.listToItem = listToItem;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAttachments() {
        return attachments;
    }

    public void setAttachments(boolean attachments) {
        this.attachments = attachments;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public void setTrashed(boolean trashed) {
        this.trashed = trashed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ConvMessageItem> getListConvMessageItems() {
        return listConvMessageItems;
    }

    public void setListConvMessageItems(ArrayList<ConvMessageItem> listConvMessageItems) {
        this.listConvMessageItems = listConvMessageItems;
    }

    public ResultItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.subject);
        dest.writeTypedList(this.listFromItem);
        dest.writeTypedList(this.listToItem);
        dest.writeString(this.body);
        dest.writeString(this.date);
        dest.writeByte(this.attachments ? (byte) 1 : (byte) 0);
        dest.writeByte(this.starred ? (byte) 1 : (byte) 0);
        dest.writeByte(this.trashed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.unread ? (byte) 1 : (byte) 0);
        dest.writeInt(this.count);
        dest.writeString(this.type);
        dest.writeTypedList(this.listConvMessageItems);
    }

    protected ResultItem(Parcel in) {
        this.id = in.readString();
        this.subject = in.readString();
        this.listFromItem = in.createTypedArrayList(Target.CREATOR);
        this.listToItem = in.createTypedArrayList(Target.CREATOR);
        this.body = in.readString();
        this.date = in.readString();
        this.attachments = in.readByte() != 0;
        this.starred = in.readByte() != 0;
        this.trashed = in.readByte() != 0;
        this.deleted = in.readByte() != 0;
        this.unread = in.readByte() != 0;
        this.count = in.readInt();
        this.type = in.readString();
        this.listConvMessageItems = in.createTypedArrayList(ConvMessageItem.CREATOR);
    }

    public static final Creator<ResultItem> CREATOR = new Creator<ResultItem>() {
        @Override
        public ResultItem createFromParcel(Parcel source) {
            return new ResultItem(source);
        }

        @Override
        public ResultItem[] newArray(int size) {
            return new ResultItem[size];
        }
    };
}
