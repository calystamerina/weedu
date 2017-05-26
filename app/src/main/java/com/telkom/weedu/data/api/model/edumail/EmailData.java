package com.telkom.weedu.data.api.model.edumail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class EmailData extends MailEdumailData {
    @SerializedName("end")
    private int end;
    @SerializedName("q")
    private String inbox;
    @SerializedName("pagination")
    private Pagination pagination;
}
