package com.telkom.weedu.view.dashboard;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.History;

import java.util.LinkedList;

public interface DashboardView extends IBaseView {
    void showHistoryList(LinkedList<History> histories);

}
