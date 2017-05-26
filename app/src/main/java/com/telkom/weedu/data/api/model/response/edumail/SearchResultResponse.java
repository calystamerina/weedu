package com.telkom.weedu.data.api.model.response.edumail;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.SearchResultData;

/**
 * Created by sidiqpermana on 5/1/17.
 */

public class SearchResultResponse extends BaseEdumailResponse{
    @SerializedName("data")
    private SearchResultData data;

    public SearchResultData getData() {
        return data;
    }

    public void setData(SearchResultData data) {
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

    public SearchResultResponse() {
    }

    protected SearchResultResponse(Parcel in) {
        super(in);
        this.data = in.readParcelable(SearchResultData.class.getClassLoader());
    }

    public static final Creator<SearchResultResponse> CREATOR = new Creator<SearchResultResponse>() {
        @Override
        public SearchResultResponse createFromParcel(Parcel source) {
            return new SearchResultResponse(source);
        }

        @Override
        public SearchResultResponse[] newArray(int size) {
            return new SearchResultResponse[size];
        }
    };
}
