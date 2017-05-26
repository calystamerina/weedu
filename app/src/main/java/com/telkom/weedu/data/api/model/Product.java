package com.telkom.weedu.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class Product implements Parcelable {
    // TODO: 3/28/17 update pojo according to api response

    private int image;
    private String name;
    private String description;
    private String url;
    private boolean needAuth;

    public Product(int image, String name, String description, String url, boolean needAuth) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.url = url;
        this.needAuth = needAuth;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isNeedAuth() {
        return needAuth;
    }

    public String getUrl() {
        return url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.image);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeByte(this.needAuth ? (byte) 1 : (byte) 0);
    }

    protected Product(Parcel in) {
        this.image = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.needAuth = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
