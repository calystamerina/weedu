package com.telkom.weedu.view.composeemail;

import com.telkom.weedu.base.BasePresenter;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.DataManager;
import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadStatus;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadedItem;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.api.model.request.PostmailRequest;
import com.telkom.weedu.data.api.model.request.ReplymailRequest;
import com.telkom.weedu.data.api.model.response.edumail.AttachmentUploadedResponse;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.FileUtils;
import com.telkom.weedu.view.mailbox.MailboxPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public class ComposePresenter <V extends ComposeView> extends BasePresenter<V> implements IComposePresenter<V>,
        ApiCallback.OnPostMailRequestCallback,
        ApiCallback.OnPostReplyMailRequestCallback, ApiCallback.OnPostUploadAttachmentCallback,
        ApiCallback.OnPostDraftRequestCallback{
    @Inject
    public ComposePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onPostMailRequestSuccess(BaseEdumailResponse baseEdumailResponse) {
        getView().hideLoading();
        getView().showPostMailSuccessMessage(baseEdumailResponse.getStatus());
    }

    @Override
    public void onPostMailRequestFailed(String message) {
        getView().hideLoading();
        getView().showPostMailFailedMessage(message);
    }

    @Override
    public void postNewEmail(String recepient, String cc, String bcc, String subject, String bodyEmail,
                             ArrayList<AttachmentUploadedItem> attachments) {
        getView().showLoading();
        PostmailRequest postmailRequest;
        if (attachments != null){
            postmailRequest = PostmailRequest.getPostMailRequest(getDataManager().getUserPreference().getEdumailToken(),
                    recepient, subject, bodyEmail, cc, bcc, attachments);
        }else{
            postmailRequest = PostmailRequest.getPostMailRequest(getDataManager().getUserPreference().getEdumailToken(),
                    recepient, subject, bodyEmail, cc, bcc);
        }
        getDataManager().composeEmail(postmailRequest, this);
    }

    @Override
    public void postReply(String cc, String bcc, String subject, String body, ReplyData replyData,
                          ArrayList<AttachmentUploadedItem> attachments) {
        getView().showLoading();
        ReplymailRequest replymailRequest = ReplymailRequest
                .getReplyMailRequest(getDataManager().getUserPreference().getEdumailToken(),
                subject, body, replyData);
        replymailRequest.setCc(cc);
        replymailRequest.setBcc(bcc);
        if (attachments != null){
            replymailRequest.setListAttachments(attachments);
        }

        getDataManager().postReplyMail(replymailRequest, this);
    }

    @Override
    public void uploadAttachment(String filePath) {
        String filename = FileUtils.getFileName(filePath);
        AttachmentUploadStatus attachmentUploadStatus = new AttachmentUploadStatus(filePath, filename,
                AppConstants.EDUMAIL_ATTACHMENT_ON_UPLOAD_PROGRESS);
        getView().onUploadAttachmentProgress(attachmentUploadStatus);
        MailRequest mailRequest = MailRequest.getMailRequest(null, getDataManager().getUserPreference().getEdumailToken());
        getDataManager().postUploadAttachment(mailRequest, filePath, this);
    }

    @Override
    public void saveMessageToDraft(String recepient, String cc, String bcc, String subject, String bodyEmail) {
        PostmailRequest postmailRequest =
                PostmailRequest.getPostMailRequest(getDataManager().getUserPreference().getEdumailToken(),
                        recepient, subject, bodyEmail, cc, bcc);

        getView().showLoading();

        getDataManager().postDraft(postmailRequest, this);
    }

    @Override
    public void onPostReplyMailRequestSuccess(String message) {
        getView().hideLoading();
        getView().onReplyMailSuccess(message);
    }

    @Override
    public void onPostReplyMailRequestFailed(String message) {
        getView().hideLoading();
        getView().onReplyMailFailed(message);
    }

    @Override
    public void onPostUploadAttachmentSuccess(String filename, AttachmentUploadedResponse attachmentUploadedResponse) {
        getView().onUploadAttachmentSuccess(filename, attachmentUploadedResponse.getItem());
    }

    @Override
    public void onPostUploadAttachmentFailed(String filename, String message) {
        getView().onUploadAttachmentFailed(filename, message);
    }

    @Override
    public void onPostDraftReqeustSuccess(BaseEdumailResponse baseEdumailResponse) {
        getView().onSaveMessageToDraftSuccess(baseEdumailResponse.getStatus());
    }

    @Override
    public void onPostDraftRequestFailed(String message) {
        getView().onSaveMessageToDraftFailed(message);
    }
}
