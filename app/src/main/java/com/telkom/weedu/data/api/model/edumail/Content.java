package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class Content implements Parcelable {
    @SerializedName("Headers")
    private Headers headers;
    @SerializedName("TextBody")
    private String textBody;
    @SerializedName("HtmlBody")
    private String htmlBody;
    @SerializedName("Size")
    private int size;
    @SerializedName("Body")
    private String body;

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.headers, flags);
        dest.writeString(this.textBody);
        dest.writeString(this.htmlBody);
        dest.writeInt(this.size);
        dest.writeString(this.body);
    }

    public Content() {
    }

    protected Content(Parcel in) {
        this.headers = in.readParcelable(Headers.class.getClassLoader());
        this.textBody = in.readString();
        this.htmlBody = in.readString();
        this.size = in.readInt();
        this.body = in.readString();
    }

    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
