package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class Mime implements Parcelable {
    @SerializedName("Parts")
    private ArrayList<PartItem> listPart = new ArrayList<>();

    public ArrayList<PartItem> getListPart() {
        return listPart;
    }

    public void setListPart(ArrayList<PartItem> listPart) {
        this.listPart = listPart;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.listPart);
    }

    public Mime() {
    }

    protected Mime(Parcel in) {
        this.listPart = in.createTypedArrayList(PartItem.CREATOR);
    }

    public static final Parcelable.Creator<Mime> CREATOR = new Parcelable.Creator<Mime>() {
        @Override
        public Mime createFromParcel(Parcel source) {
            return new Mime(source);
        }

        @Override
        public Mime[] newArray(int size) {
            return new Mime[size];
        }
    };
}
