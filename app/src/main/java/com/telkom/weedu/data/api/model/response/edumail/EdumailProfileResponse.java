package com.telkom.weedu.data.api.model.response.edumail;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.ProfileData;

/**
 * Created by sidiqpermana on 4/25/17.
 */

public class EdumailProfileResponse extends BaseEdumailResponse {
    @SerializedName("data")
    private ProfileData data;

    public ProfileData getData() {
        return data;
    }

    public void setData(ProfileData data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.data, flags);
    }

    public EdumailProfileResponse() {
    }

    protected EdumailProfileResponse(Parcel in) {
        super(in);
        this.data = in.readParcelable(ProfileData.class.getClassLoader());
    }

    public static final Creator<EdumailProfileResponse> CREATOR = new Creator<EdumailProfileResponse>() {
        @Override
        public EdumailProfileResponse createFromParcel(Parcel source) {
            return new EdumailProfileResponse(source);
        }

        @Override
        public EdumailProfileResponse[] newArray(int size) {
            return new EdumailProfileResponse[size];
        }
    };
}
