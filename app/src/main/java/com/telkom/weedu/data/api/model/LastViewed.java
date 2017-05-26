package com.telkom.weedu.data.api.model;

/**
 * Created by ghiyatshanif on 3/30/17.
 */

public class LastViewed {
    // TODO: 3/28/17 update pojo according to api response

    private int image;
    private String title;
    private String description;
    private String source;

    public LastViewed(int image, String title, String description, String source) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.source = source;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
