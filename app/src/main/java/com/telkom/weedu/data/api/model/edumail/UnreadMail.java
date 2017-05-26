package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/21/17.
 */

public class UnreadMail implements Parcelable {
    @SerializedName("unread")
    private int unread;

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.unread);
    }

    public UnreadMail() {
    }

    protected UnreadMail(Parcel in) {
        this.unread = in.readInt();
    }

    public static final Parcelable.Creator<UnreadMail> CREATOR = new Parcelable.Creator<UnreadMail>() {
        @Override
        public UnreadMail createFromParcel(Parcel source) {
            return new UnreadMail(source);
        }

        @Override
        public UnreadMail[] newArray(int size) {
            return new UnreadMail[size];
        }
    };
}
