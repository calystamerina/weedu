package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class ForwardData implements Parcelable {
    @SerializedName("Date")
    private ArrayList<String> listDate = new ArrayList<>();

    @SerializedName("From")
    private ArrayList<String> listFrom = new ArrayList<>();

    @SerializedName("HtmlBody")
    private String htmlBody;

    @SerializedName("Subject")
    private ArrayList<String> listSubject = new ArrayList<>();

    @SerializedName("TextBody")
    private String textBody;

    public ArrayList<String> getListDate() {
        return listDate;
    }

    public void setListDate(ArrayList<String> listDate) {
        this.listDate = listDate;
    }

    public ArrayList<String> getListFrom() {
        return listFrom;
    }

    public void setListFrom(ArrayList<String> listFrom) {
        this.listFrom = listFrom;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public ArrayList<String> getListSubject() {
        return listSubject;
    }

    public void setListSubject(ArrayList<String> listSubject) {
        this.listSubject = listSubject;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.listDate);
        dest.writeStringList(this.listFrom);
        dest.writeString(this.htmlBody);
        dest.writeStringList(this.listSubject);
        dest.writeString(this.textBody);
    }

    public ForwardData() {
    }

    protected ForwardData(Parcel in) {
        this.listDate = in.createStringArrayList();
        this.listFrom = in.createStringArrayList();
        this.htmlBody = in.readString();
        this.listSubject = in.createStringArrayList();
        this.textBody = in.readString();
    }

    public static final Parcelable.Creator<ForwardData> CREATOR = new Parcelable.Creator<ForwardData>() {
        @Override
        public ForwardData createFromParcel(Parcel source) {
            return new ForwardData(source);
        }

        @Override
        public ForwardData[] newArray(int size) {
            return new ForwardData[size];
        }
    };
}
