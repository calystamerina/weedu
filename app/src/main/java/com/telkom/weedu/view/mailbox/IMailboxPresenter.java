package com.telkom.weedu.view.mailbox;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.view.maildetail.IMailDetailPresenter;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public interface IMailboxPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void openComposeActivity();
    void loadMail(String mailBoxType, int page);
    void openMailDetailActivity(Mail mail);
    void removeMailToTrash(String mailId);
    void removeMailPermanently(String mailId);
}
