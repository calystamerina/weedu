package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class PartItem implements Parcelable {
    @SerializedName("Headers")
    private Headers headers;
    @SerializedName("Body")
    private String body;
    @SerializedName("FileName")
    private String fileName;
    @SerializedName("ContentType")
    private String contentType;
    @SerializedName("Charset")
    private String charset;
    @SerializedName("MIMEVersion")
    private String mimeVersion;
    @SerializedName("TransferEncoding")
    private String transferEncoding;
    @SerializedName("Disposition")
    private String disposition;
    @SerializedName("Size")
    private String size;

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getMimeVersion() {
        return mimeVersion;
    }

    public void setMimeVersion(String mimeVersion) {
        this.mimeVersion = mimeVersion;
    }

    public String getTransferEncoding() {
        return transferEncoding;
    }

    public void setTransferEncoding(String transferEncoding) {
        this.transferEncoding = transferEncoding;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.headers, flags);
        dest.writeString(this.body);
        dest.writeString(this.fileName);
        dest.writeString(this.contentType);
        dest.writeString(this.charset);
        dest.writeString(this.mimeVersion);
        dest.writeString(this.transferEncoding);
        dest.writeString(this.disposition);
        dest.writeString(this.size);
    }

    public PartItem() {
    }

    protected PartItem(Parcel in) {
        this.headers = in.readParcelable(Headers.class.getClassLoader());
        this.body = in.readString();
        this.fileName = in.readString();
        this.contentType = in.readString();
        this.charset = in.readString();
        this.mimeVersion = in.readString();
        this.transferEncoding = in.readString();
        this.disposition = in.readString();
        this.size = in.readString();
    }

    public static final Parcelable.Creator<PartItem> CREATOR = new Parcelable.Creator<PartItem>() {
        @Override
        public PartItem createFromParcel(Parcel source) {
            return new PartItem(source);
        }

        @Override
        public PartItem[] newArray(int size) {
            return new PartItem[size];
        }
    };
}
