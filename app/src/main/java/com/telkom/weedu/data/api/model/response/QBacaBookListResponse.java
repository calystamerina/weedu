package com.telkom.weedu.data.api.model.response;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.data.api.model.QBacaBook;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sidiqpermana on 4/6/17.
 */

public class QBacaBookListResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private LinkedList<QBacaBook> listData = new LinkedList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LinkedList<QBacaBook> getListData() {
        return listData;
    }

    public void setListData(LinkedList<QBacaBook> listData) {
        this.listData = listData;
    }
}
