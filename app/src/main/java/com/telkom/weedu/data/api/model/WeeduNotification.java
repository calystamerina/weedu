package com.telkom.weedu.data.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghiyats on 11/9/2016.
 */

public class WeeduNotification {
    @SerializedName("related_id")
    private int relatedId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;
    @SerializedName("content")
    private String content;
    @SerializedName("date")
    private String date;
    @SerializedName("sender_name")
    private String sender;

    public int getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(int relatedId) {
        this.relatedId = relatedId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        if (date == null) {
            date = new String("2016-01-01T00:00:00.000Z");
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
