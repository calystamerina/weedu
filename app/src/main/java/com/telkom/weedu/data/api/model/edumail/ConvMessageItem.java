package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class ConvMessageItem implements Parcelable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Subject")
    private String subject;
    @SerializedName("From")
    private Target from;
    @SerializedName("To")
    private ArrayList<Target> listTo = new ArrayList<>();
    @SerializedName("Created")
    private String created;
    @SerializedName("Attachments")
    private ArrayList<AttachmentItem> listAttachment = new ArrayList<>();
    @SerializedName("Embeddeds")
    private ArrayList<String> listEmbeddeds = new ArrayList<>();
    @SerializedName("Ip")
    private String ip;
    @SerializedName("Content")
    private Content content;
    @SerializedName("MIME")
    private Mime mime;
    @SerializedName("Owner")
    private String owner;
    @SerializedName("User")
    private User user;
    @SerializedName("ConvId")
    private String convId;
    @SerializedName("Type")
    private String type;
    @SerializedName("Starred")
    private String starred;
    @SerializedName("Trashed")
    private String trashed;
    @SerializedName("Deleted")
    private String deleted;
    @SerializedName("Unread")
    private String unread;

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

    public ArrayList<Target> getListTo() {
        return listTo;
    }

    public void setListTo(ArrayList<Target> listTo) {
        this.listTo = listTo;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public ArrayList<String> getListEmbeddeds() {
        return listEmbeddeds;
    }

    public void setListEmbeddeds(ArrayList<String> listEmbeddeds) {
        this.listEmbeddeds = listEmbeddeds;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Mime getMime() {
        return mime;
    }

    public void setMime(Mime mime) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStarred() {
        return starred;
    }

    public void setStarred(String starred) {
        this.starred = starred;
    }

    public String getTrashed() {
        return trashed;
    }

    public void setTrashed(String trashed) {
        this.trashed = trashed;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    public Target getFrom() {
        return from;
    }

    public void setFrom(Target from) {
        this.from = from;
    }

    public ArrayList<AttachmentItem> getListAttachment() {
        return listAttachment;
    }

    public void setListAttachment(ArrayList<AttachmentItem> listAttachment) {
        this.listAttachment = listAttachment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        dest.writeTypedList(this.listTo);
        dest.writeString(this.created);
        dest.writeTypedList(this.listAttachment);
        dest.writeStringList(this.listEmbeddeds);
        dest.writeString(this.ip);
        dest.writeParcelable(this.content, flags);
        dest.writeParcelable(this.mime, flags);
        dest.writeString(this.owner);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.convId);
        dest.writeString(this.type);
        dest.writeString(this.starred);
        dest.writeString(this.trashed);
        dest.writeString(this.deleted);
        dest.writeString(this.unread);
    }

    public ConvMessageItem() {
    }

    protected ConvMessageItem(Parcel in) {
        this.id = in.readString();
        this.subject = in.readString();
        this.from = in.readParcelable(Target.class.getClassLoader());
        this.listTo = in.createTypedArrayList(Target.CREATOR);
        this.created = in.readString();
        this.listAttachment = in.createTypedArrayList(AttachmentItem.CREATOR);
        this.listEmbeddeds = in.createStringArrayList();
        this.ip = in.readString();
        this.content = in.readParcelable(Content.class.getClassLoader());
        this.mime = in.readParcelable(Mime.class.getClassLoader());
        this.owner = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.convId = in.readString();
        this.type = in.readString();
        this.starred = in.readString();
        this.trashed = in.readString();
        this.deleted = in.readString();
        this.unread = in.readString();
    }

    public static final Creator<ConvMessageItem> CREATOR = new Creator<ConvMessageItem>() {
        @Override
        public ConvMessageItem createFromParcel(Parcel source) {
            return new ConvMessageItem(source);
        }

        @Override
        public ConvMessageItem[] newArray(int size) {
            return new ConvMessageItem[size];
        }
    };
}
