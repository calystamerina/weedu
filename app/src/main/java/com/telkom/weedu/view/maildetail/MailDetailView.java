package com.telkom.weedu.view.maildetail;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.edumail.ForwardData;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.mapper.edumail.Mail;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public interface MailDetailView extends IBaseView {
    void setUpHeaderList(Mail mail);
    void setUpFooterList();
    void showInitialLoading();
    void hideInitialLoading();
    void showMailDetail(Mail mail);
    void showMailDetailError(String message);
    void onRemoveMailToTrashSuccess(String message, String mailId);
    void onRemoveMailToTrashFailed(String message);
    void onRemoveMailPermanentlySucess(String message, String mailId);
    void onRemoveMailPermanentlyFailed(String message);
    void onAddRemoveFavoriteSuccess(String message, Mail mail);
    void onAddRemoveFavoriteFailed(String message);
    void openComposeActivityForReply(ReplyData replyData);
    void onGetReplyReferenceFailed(String message);
    void openComposetActivityForReplyAll(ReplyData replyData);
    void onGetReplyAllReferenceFailed(String message);
    void openComposeActivityForForward(ForwardData forwardData);
    void onGetForwardReferenceFailed(String message);
}
