package com.telkom.weedu.data.api.model.edumail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/13/17.
 */

public class AttachmentItem implements Parcelable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Body")
    private String body;
    @SerializedName("UrlPath")
    private String urlPath;
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
    @SerializedName("Size")
    private String size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
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
        dest.writeString(this.id);
        dest.writeString(this.body);
        dest.writeString(this.urlPath);
        dest.writeString(this.fileName);
        dest.writeString(this.contentType);
        dest.writeString(this.charset);
        dest.writeString(this.mimeVersion);
        dest.writeString(this.transferEncoding);
        dest.writeString(this.size);
    }

    public AttachmentItem() {
    }

    protected AttachmentItem(Parcel in) {
        this.id = in.readString();
        this.body = in.readString();
        this.urlPath = in.readString();
        this.fileName = in.readString();
        this.contentType = in.readString();
        this.charset = in.readString();
        this.mimeVersion = in.readString();
        this.transferEncoding = in.readString();
        this.size = in.readString();
    }

    public static final Parcelable.Creator<AttachmentItem> CREATOR = new Parcelable.Creator<AttachmentItem>() {
        @Override
        public AttachmentItem createFromParcel(Parcel source) {
            return new AttachmentItem(source);
        }

        @Override
        public AttachmentItem[] newArray(int size) {
            return new AttachmentItem[size];
        }
    };
}
