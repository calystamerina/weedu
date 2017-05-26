package com.telkom.weedu.data.api.model.response;

import com.google.gson.annotations.SerializedName;
import com.telkom.weedu.data.api.model.History;

import java.util.LinkedList;

/**
 * Created by ghiyatshanif on 4/25/17.
 */

public class HistoryResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private LinkedList<History> histories;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LinkedList<History> getHistories() {
        return histories;
    }

    public void setHistories(LinkedList<History> histories) {
        this.histories = histories;
    }
}
