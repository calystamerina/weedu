package com.telkom.weedu.data.mapper.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.telkom.weedu.data.api.model.edumail.ConvMessageItem;
import com.telkom.weedu.data.api.model.edumail.ResultItem;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/13/17.
 */

public class Conversation implements Parcelable {
    private String senderName;
    private String receiverNames;
    private String date;
    private String senderPhotoUrl;
    private String emailBody;
    private ArrayList<Attachment> attachments;

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverNames() {
        return receiverNames;
    }

    public void setReceiverNames(String receiverNames) {
        this.receiverNames = receiverNames;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSenderPhotoUrl() {
        return senderPhotoUrl;
    }

    public void setSenderPhotoUrl(String senderPhotoUrl) {
        this.senderPhotoUrl = senderPhotoUrl;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public Conversation() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.senderName);
        dest.writeString(this.receiverNames);
        dest.writeString(this.date);
        dest.writeString(this.senderPhotoUrl);
        dest.writeString(this.emailBody);
        dest.writeList(this.attachments);
    }

    protected Conversation(Parcel in) {
        this.senderName = in.readString();
        this.receiverNames = in.readString();
        this.date = in.readString();
        this.senderPhotoUrl = in.readString();
        this.emailBody = in.readString();
        this.attachments = new ArrayList<Attachment>();
        in.readList(this.attachments, Attachment.class.getClassLoader());
    }

    public static final Creator<Conversation> CREATOR = new Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel source) {
            return new Conversation(source);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };
}
