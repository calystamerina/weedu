package com.telkom.weedu.data.api.model;

import com.telkom.weedu.utils.AppConstants;

/**
 * Created by ghiyatshanif on 3/28/17.
 */

public class Recomendation {
    // TODO: 3/28/17 update pojo according to api response

    private String image;
    private String title;
    private String source;
    private QBacaBook  qBacaBook;

    public Recomendation(String image, String title, String source) {
        this.image = image;
        this.title = title;
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public QBacaBook getqBacaBook() {
        return qBacaBook;
    }

    public void setqBacaBook(QBacaBook qBacaBook) {
        this.qBacaBook = qBacaBook;
    }

    public static Recomendation getRecommendation(QBacaBook qBacaBook){
        Recomendation recomendation = new Recomendation(qBacaBook.getImage(), qBacaBook.getTitle(), AppConstants.SOURCE_QBACA);
        recomendation.setqBacaBook(qBacaBook);

        return recomendation;
    }
}
