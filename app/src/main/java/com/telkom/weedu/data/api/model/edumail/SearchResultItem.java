package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 5/1/17.
 */

public class SearchResultItem implements Parcelable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Subject")
    private String subject;
    @SerializedName("From")
    private Target from;
    @SerializedName("To")
    private ArrayList<Target> listToItem = new ArrayList<>();
    @SerializedName("Created")
    private String created;
    @SerializedName("Attachments")
    private ArrayList<AttachmentItem> attachments = new ArrayList<>();
    @SerializedName("Content")
    private Content content;
    @SerializedName("MIME")
    private String mime;
    @SerializedName("Owner")
    private String owner;
    @SerializedName("ConvId")
    private String convId;
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


    public ArrayList<Target> getListToItem() {
        return listToItem;
    }

    public void setListToItem(ArrayList<Target> listToItem) {
        this.listToItem = listToItem;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ArrayList<AttachmentItem> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<AttachmentItem> attachments) {
        this.attachments = attachments;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getConvId() {
        return convId;
    }

    public void setConvId(String convId) {
        this.convId = convId;
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

    public SearchResultItem() {
    }

    public Target getFrom() {
        return from;
    }

    public void setFrom(Target from) {
        this.from = from;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.subject);
        dest.writeParcelable(this.from, flags);
        dest.writeTypedList(this.listToItem);
        dest.writeString(this.created);
        dest.writeTypedList(this.attachments);
        dest.writeParcelable(this.content, flags);
        dest.writeString(this.mime);
        dest.writeString(this.owner);
        dest.writeString(this.convId);
        dest.writeByte(this.starred ? (byte) 1 : (byte) 0);
        dest.writeByte(this.trashed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.unread ? (byte) 1 : (byte) 0);
        dest.writeInt(this.count);
        dest.writeString(this.type);
    }

    protected SearchResultItem(Parcel in) {
        this.id = in.readString();
        this.subject = in.readString();
        this.from = in.readParcelable(Target.class.getClassLoader());
        this.listToItem = in.createTypedArrayList(Target.CREATOR);
        this.created = in.readString();
        this.attachments = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.content = in.readParcelable(Content.class.getClassLoader());
        this.mime = in.readString();
        this.owner = in.readString();
        this.convId = in.readString();
        this.starred = in.readByte() != 0;
        this.trashed = in.readByte() != 0;
        this.deleted = in.readByte() != 0;
        this.unread = in.readByte() != 0;
        this.count = in.readInt();
        this.type = in.readString();
    }

    public static final Creator<SearchResultItem> CREATOR = new Creator<SearchResultItem>() {
        @Override
        public SearchResultItem createFromParcel(Parcel source) {
            return new SearchResultItem(source);
        }

        @Override
        public SearchResultItem[] newArray(int size) {
            return new SearchResultItem[size];
        }
    };
}
