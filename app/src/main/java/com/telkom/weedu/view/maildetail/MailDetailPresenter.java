package com.telkom.weedu.view.maildetail;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.edumail.ForwardData;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.api.model.request.ReplymailRequest;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.view.mail.IMailPresenter;
import com.telkom.weedu.view.mail.MailView;
import com.telkom.weedu.view.mailbox.MailboxPresenter;

import javax.inject.Inject;

/**
 * Created by sidiqpermana on 4/12/17.
 */

public class MailDetailPresenter<V extends MailDetailView> extends BasePresenter<V> implements IMailDetailPresenter<V>,
        ApiCallback.OnGetMailDetailRequestCallback, ApiCallback.OnPostStaringMailRequestCallback,
        ApiCallback.OnPostRemoveMailToTrashCallback, ApiCallback.OnPostRemoveMailPermanentlyRequestCallback,
        ApiCallback.OnGetReplyReferenceRequestCallback, ApiCallback.OnGetReplyAllReferenceRequestCallback,
        ApiCallback.OnGetForwardReferenceRequestCallback{
    @Inject
    public MailDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void loadMailDetail(String mailId) {
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, MailboxPresenter.edumailToken);

        getView().showInitialLoading();
        getDataManager().loadMailDetail(mailRequest, this);
    }

    @Override
    public void favoriteMail(Mail mail, boolean isStarred) {
        MailRequest mailRequest = MailRequest.getMailRequest(mail.getMailId(), MailboxPresenter.edumailToken);
        mailRequest.setMail(mail);
        mailRequest.setStarred(isStarred);

        getView().showLoading();
        getDataManager().postAddRemoveStarInMail(mailRequest, this);
    }

    @Override
    public void removeMailToTrash(String mailId) {
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, MailboxPresenter.edumailToken);

        getView().showLoading();
        getDataManager().postRemoveMailToTrash(mailRequest, this);
    }

    @Override
    public void removeMailPermanently(String mailId) {
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, MailboxPresenter.edumailToken);

        getView().showLoading();
        getDataManager().postRemoveMailPermanently(mailRequest, this);
    }

    @Override
    public void getReplyMailPreference(String mailId) {
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, MailboxPresenter.edumailToken);

        getView().showLoading();
        getDataManager().getReplyPreference(mailRequest, this);
    }

    @Override
    public void getReplyAllMailPreference(String mailId) {
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, MailboxPresenter.edumailToken);

        getView().showLoading();
        getDataManager().getReplyAllPreference(mailRequest, this);
    }

    @Override
    public void getForwardMailPreference(String mailId) {
        MailRequest mailRequest = MailRequest.getMailRequest(mailId, MailboxPresenter.edumailToken);

        getView().showLoading();
        getDataManager().getForwardPreference(mailRequest, this);
    }

    @Override
    public void onGetMailDetailSuccess(Mail mail) {
        getView().hideInitialLoading();
        getView().showMailDetail(mail);
    }

    @Override
    public void onGetMailDetailFailed(String errorMessage) {
        getView().hideInitialLoading();
        getView().showMailDetailError(errorMessage);
    }

    @Override
    public void onPostStaringMailRequestSuccess(BaseEdumailResponse baseEdumailResponse, Mail mail) {
        getView().hideLoading();
        getView().onAddRemoveFavoriteSuccess(baseEdumailResponse.getStatus(), mail);
    }

    @Override
    public void onPostStaringMailRequestFailed(String message) {
        getView().hideLoading();
        getView().onAddRemoveFavoriteFailed(message);
    }

    @Override
    public void onPostRemoveMailToTrashRequestSuccess(BaseEdumailResponse baseEdumailResponse, String mailId) {
        getView().hideLoading();
        getView().onRemoveMailToTrashSuccess(baseEdumailResponse.getStatus(), mailId);
    }

    @Override
    public void onPostRemoveMailToTrashRequestFailed(String message) {
        getView().hideLoading();
        getView().onRemoveMailToTrashFailed(message);
    }

    @Override
    public void onPostRemoveMailPermanentlySuccess(BaseEdumailResponse baseEdumailResponse, String mailId) {
        getView().hideLoading();
        getView().onRemoveMailPermanentlySucess(baseEdumailResponse.getStatus(), mailId);
    }

    @Override
    public void onPostRemoveMailPermanentlyFailed(String message) {
        getView().hideLoading();
        getView().onRemoveMailPermanentlyFailed(message);
    }

    @Override
    public void onGetReplyReferenceRequestSuccess(ReplyData replyData) {
        getView().hideLoading();
        getView().openComposeActivityForReply(replyData);
    }

    @Override
    public void onGetReplyReferenceRequestFailed(String message) {
        getView().hideLoading();
        getView().onGetReplyReferenceFailed(message);
    }

    @Override
    public void onGetReplyAllReferenceRequestSuccess(ReplyData replyData) {
        getView().hideLoading();
        getView().openComposetActivityForReplyAll(replyData);
    }

    @Override
    public void onGetReplyAllRefferenceRequestFailed(String message) {
        getView().hideLoading();
        getView().onGetReplyAllReferenceFailed(message);
    }

    @Override
    public void onGetForwardReferenceRequestSuccess(ForwardData forwardData) {
        getView().hideLoading();
        getView().openComposeActivityForForward(forwardData);
    }

    @Override
    public void onGetForwardReferenceRequestFailed(String message) {
        getView().hideLoading();
        getView().onGetForwardReferenceFailed(message);
    }
}
