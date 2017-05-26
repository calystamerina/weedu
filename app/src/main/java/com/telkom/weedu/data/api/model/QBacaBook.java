package com.telkom.weedu.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/6/17.
 */

public class QBacaBook implements Parcelable {
    @SerializedName("book_id")
    private String bookId;

    @SerializedName("book_title")
    private String title;

    @SerializedName("isbn")
    private String isbn;

    @SerializedName("book_img")
    private String image;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("publisher_id")
    private String publisherId;

    @SerializedName("publisher_name")
    private String publisherName;

    @SerializedName("price")
    private String price;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("sinopsis")
    private String sinopsis;

    @SerializedName("authors")
    private String authors;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookId);
        dest.writeString(this.title);
        dest.writeString(this.isbn);
        dest.writeString(this.image);
        dest.writeString(this.categoryId);
        dest.writeString(this.categoryName);
        dest.writeString(this.publisherId);
        dest.writeString(this.publisherName);
        dest.writeString(this.price);
        dest.writeString(this.jumlah);
        dest.writeString(this.sinopsis);
        dest.writeString(this.authors);
    }

    public QBacaBook() {
    }

    protected QBacaBook(Parcel in) {
        this.bookId = in.readString();
        this.title = in.readString();
        this.isbn = in.readString();
        this.image = in.readString();
        this.categoryId = in.readString();
        this.categoryName = in.readString();
        this.publisherId = in.readString();
        this.publisherName = in.readString();
        this.price = in.readString();
        this.jumlah = in.readString();
        this.sinopsis = in.readString();
        this.authors = in.readString();
    }

    public static final Parcelable.Creator<QBacaBook> CREATOR = new Parcelable.Creator<QBacaBook>() {
        @Override
        public QBacaBook createFromParcel(Parcel source) {
            return new QBacaBook(source);
        }

        @Override
        public QBacaBook[] newArray(int size) {
            return new QBacaBook[size];
        }
    };
}
