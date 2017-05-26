package com.telkom.weedu.view.maildetail;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.mapper.edumail.Mail;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public interface IMailDetailPresenter<V extends IBaseView> extends IBasePresenter<V> {
    void loadMailDetail(String mailId);
    void favoriteMail(Mail mail, boolean isStarred);
    void removeMailToTrash(String mailId);
    void removeMailPermanently(String mailId);
    void getReplyMailPreference(String mailId);
    void getReplyAllMailPreference(String mailId);
    void getForwardMailPreference(String mailId);
}
