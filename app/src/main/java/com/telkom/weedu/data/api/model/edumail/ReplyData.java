package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class ReplyData implements Parcelable {
    @SerializedName("Cc")
    private String cc;

    @SerializedName("References")
    private String references;

    @SerializedName("ReplyTo")
    private String replyTo;

    @SerializedName("Subject")
    private ArrayList<String> listSubject = new ArrayList<>();

    @SerializedName("To")
    private String to;

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public ArrayList<String> getListSubject() {
        return listSubject;
    }

    public void setListSubject(ArrayList<String> listSubject) {
        this.listSubject = listSubject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ReplyData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cc);
        dest.writeString(this.references);
        dest.writeString(this.replyTo);
        dest.writeStringList(this.listSubject);
        dest.writeString(this.to);
    }

    protected ReplyData(Parcel in) {
        this.cc = in.readString();
        this.references = in.readString();
        this.replyTo = in.readString();
        this.listSubject = in.createStringArrayList();
        this.to = in.readString();
    }

    public static final Creator<ReplyData> CREATOR = new Creator<ReplyData>() {
        @Override
        public ReplyData createFromParcel(Parcel source) {
            return new ReplyData(source);
        }

        @Override
        public ReplyData[] newArray(int size) {
            return new ReplyData[size];
        }
    };
}
