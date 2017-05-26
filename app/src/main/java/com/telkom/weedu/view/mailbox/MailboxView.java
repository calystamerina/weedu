package com.telkom.weedu.view.mailbox;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.mapper.edumail.Mail;

import java.util.LinkedList;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public interface MailboxView extends IBaseView {
    void showInitialLoading();
    void hideInitialLoading();
    void toComposeActivity();
    void showInboxList(LinkedList<Mail> list);
    void showEmptyInbox(String message);
    void showErrorInInbox(String message);
    void toMailDetailActivity(Mail mail);
    void onRemoveMailToTrashSuccess(String message, String mailId);
    void onRemoveMailToTrashFailed(String message);
    void onRemoveMailPermanentlySuccess(String message, String mailId);
    void onRemoveMailPermanentlyFailed(String message);
}
