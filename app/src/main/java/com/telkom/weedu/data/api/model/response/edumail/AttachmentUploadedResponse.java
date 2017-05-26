package com.telkom.weedu.data.api.model.response.edumail;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadedItem;

/**
 * Created by sidiqpermana on 4/30/17.
 */

public class AttachmentUploadedResponse extends BaseEdumailResponse {
    @SerializedName("data")
    private AttachmentUploadedItem item;

    public AttachmentUploadedItem getItem() {
        return item;
    }

    public void setItem(AttachmentUploadedItem item) {
        this.item = item;
    }
}
