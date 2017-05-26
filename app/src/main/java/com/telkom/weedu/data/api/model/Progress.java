package com.telkom.weedu.data.api.model;

/**
 * Created by ghiyatshanif on 3/30/17.
 */

public class Progress {
    // TODO: 3/28/17 update pojo according to api response

    private int image;
    private String title;
    private String date;
    private String time;
    private String source;
    private int progress;

    public Progress(int image, String title, String date, String time, String source, int progress) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.time = time;
        this.source = source;
        this.progress = progress;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
