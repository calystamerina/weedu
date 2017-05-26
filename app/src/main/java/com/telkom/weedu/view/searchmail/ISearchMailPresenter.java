package com.telkom.weedu.view.searchmail;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.mapper.edumail.Mail;

/**
 * Created by sidiqpermana on 5/1/17.
 */

public interface ISearchMailPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void loadMail(String keyword);
    void openMailDetailActivity(Mail mail);
}
