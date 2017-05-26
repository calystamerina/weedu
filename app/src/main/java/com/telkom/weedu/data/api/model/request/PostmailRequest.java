package com.telkom.weedu.data.api.model.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.telkom.weedu.data.api.model.edumail.AttachmentUploadedItem;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/15/17.
 */

public class PostmailRequest implements Parcelable {
    private String token;
    private String recepient;
    private String body;
    private String subject;
    private String cc;
    private String bcc;
    private ArrayList<AttachmentUploadedItem> listAttachments;

    public ArrayList<AttachmentUploadedItem> getListAttachments() {
        return listAttachments;
    }

    public void setListAttachments(ArrayList<AttachmentUploadedItem> listAttachments) {
        this.listAttachments = listAttachments;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRecepient() {
        return recepient;
    }

    public void setRecepient(String recepient) {
        this.recepient = recepient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public static PostmailRequest getPostMailRequest(String token, String recepient, String subject, String body,
                                                     String cc, String bcc){
        PostmailRequest p = new PostmailRequest();
        p.setBody(body);
        p.setRecepient(recepient);
        p.setSubject(subject);
        p.setToken(token);
        p.setCc(cc);
        p.setBcc(bcc);

        return p;
    }

    public static PostmailRequest getPostMailRequest(String token, String recepient, String subject, String body,
                                                     String cc, String bcc, ArrayList<AttachmentUploadedItem> attachments){
        PostmailRequest p = new PostmailRequest();
        p.setBody(body);
        p.setRecepient(recepient);
        p.setSubject(subject);
        p.setToken(token);
        p.setCc(cc);
        p.setBcc(bcc);
        p.setListAttachments(attachments);

        return p;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.recepient);
        dest.writeString(this.body);
        dest.writeString(this.subject);
        dest.writeString(this.cc);
        dest.writeString(this.bcc);
        dest.writeList(this.listAttachments);
    }

    public PostmailRequest() {
    }

    protected PostmailRequest(Parcel in) {
        this.token = in.readString();
        this.recepient = in.readString();
        this.body = in.readString();
        this.subject = in.readString();
        this.cc = in.readString();
        this.bcc = in.readString();
        this.listAttachments = new ArrayList<AttachmentUploadedItem>();
        in.readList(this.listAttachments, AttachmentUploadedItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<PostmailRequest> CREATOR = new Parcelable.Creator<PostmailRequest>() {
        @Override
        public PostmailRequest createFromParcel(Parcel source) {
            return new PostmailRequest(source);
        }

        @Override
        public PostmailRequest[] newArray(int size) {
            return new PostmailRequest[size];
        }
    };
}
