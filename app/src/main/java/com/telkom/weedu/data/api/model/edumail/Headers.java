package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class Headers implements Parcelable {
    @SerializedName("Content-Type")
    private ArrayList<String> listContentType = new ArrayList<>();
    @SerializedName("Date")
    private ArrayList<String> listDate = new ArrayList<>();
    @SerializedName("From")
    private ArrayList<String> listFrom = new ArrayList<>();
    @SerializedName("Message-ID")
    private ArrayList<String> listMessageID = new ArrayList<>();
    @SerializedName("Message-Id")
    private ArrayList<String> listMessageId = new ArrayList<>();
    @SerializedName("Mime-Version")
    private ArrayList<String> listMimeVersion = new ArrayList<>();
    @SerializedName("Received")
    private ArrayList<String> listReeceive = new ArrayList<>();
    @SerializedName("Return-Path")
    private ArrayList<String> listReturnPath = new ArrayList<>();
    @SerializedName("Subject")
    private ArrayList<String> listSubject = new ArrayList<>();
    @SerializedName("To")
    private ArrayList<String> listTo = new ArrayList<>();

    public ArrayList<String> getListContentType() {
        return listContentType;
    }

    public void setListContentType(ArrayList<String> listContentType) {
        this.listContentType = listContentType;
    }

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

    public ArrayList<String> getListMessageID() {
        return listMessageID;
    }

    public void setListMessageID(ArrayList<String> listMessageID) {
        this.listMessageID = listMessageID;
    }

    public ArrayList<String> getListMessageId() {
        return listMessageId;
    }

    public void setListMessageId(ArrayList<String> listMessageId) {
        this.listMessageId = listMessageId;
    }

    public ArrayList<String> getListMimeVersion() {
        return listMimeVersion;
    }

    public void setListMimeVersion(ArrayList<String> listMimeVersion) {
        this.listMimeVersion = listMimeVersion;
    }

    public ArrayList<String> getListReeceive() {
        return listReeceive;
    }

    public void setListReeceive(ArrayList<String> listReeceive) {
        this.listReeceive = listReeceive;
    }

    public ArrayList<String> getListReturnPath() {
        return listReturnPath;
    }

    public void setListReturnPath(ArrayList<String> listReturnPath) {
        this.listReturnPath = listReturnPath;
    }

    public ArrayList<String> getListSubject() {
        return listSubject;
    }

    public void setListSubject(ArrayList<String> listSubject) {
        this.listSubject = listSubject;
    }

    public ArrayList<String> getListTo() {
        return listTo;
    }

    public void setListTo(ArrayList<String> listTo) {
        this.listTo = listTo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.listContentType);
        dest.writeStringList(this.listDate);
        dest.writeStringList(this.listFrom);
        dest.writeStringList(this.listMessageID);
        dest.writeStringList(this.listMessageId);
        dest.writeStringList(this.listMimeVersion);
        dest.writeStringList(this.listReeceive);
        dest.writeStringList(this.listReturnPath);
        dest.writeStringList(this.listSubject);
        dest.writeStringList(this.listTo);
    }

    public Headers() {
    }

    protected Headers(Parcel in) {
        this.listContentType = in.createStringArrayList();
        this.listDate = in.createStringArrayList();
        this.listFrom = in.createStringArrayList();
        this.listMessageID = in.createStringArrayList();
        this.listMessageId = in.createStringArrayList();
        this.listMimeVersion = in.createStringArrayList();
        this.listReeceive = in.createStringArrayList();
        this.listReturnPath = in.createStringArrayList();
        this.listSubject = in.createStringArrayList();
        this.listTo = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Headers> CREATOR = new Parcelable.Creator<Headers>() {
        @Override
        public Headers createFromParcel(Parcel source) {
            return new Headers(source);
        }

        @Override
        public Headers[] newArray(int size) {
            return new Headers[size];
        }
    };
}
