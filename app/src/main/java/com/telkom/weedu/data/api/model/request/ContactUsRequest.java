package com.telkom.weedu.data.api.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghiyatshanif on 5/1/17.
 */

public class ContactUsRequest {
    @SerializedName("productId")
    private String productId;
    @SerializedName("subject")
    private String subject;
    @SerializedName("message")
    private String message;

    public ContactUsRequest(String productId, String subject, String message) {
        this.productId = productId;
        this.subject = subject;
        this.message = message;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
