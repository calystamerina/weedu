package com.telkom.weedu.data.api.model.edumail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/30/17.
 */

public class AttachmentUploadedItem {
    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
