package com.telkom.weedu.view.searchmail;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.mapper.edumail.Mail;

import java.util.LinkedList;

/**
 * Created by sidiqpermana on 5/1/17.
 */

public interface SearchMailView extends IBaseView{
    void showInitialLoading();
    void hideInitialLoading();
    void toMailDetailActivity(Mail mail);
    void showSearchResultList(LinkedList<Mail> list);
    void showEmptyResult(String message);
    void showErrorResult(String message);
}
