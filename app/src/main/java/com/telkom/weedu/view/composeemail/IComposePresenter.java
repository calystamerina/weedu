package com.telkom.weedu.view.composeemail;

import com.telkom.weedu.base.IBasePresenter;
import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadedItem;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.api.model.request.PostmailRequest;

import java.util.ArrayList;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public interface IComposePresenter <V extends IBaseView> extends IBasePresenter<V> {
    void postNewEmail(String recepient, String cc, String bcc, String subject, String bodyEmail, ArrayList<AttachmentUploadedItem> attachments);
    void postReply(String cc, String bcc, String subject, String body, ReplyData replyData, ArrayList<AttachmentUploadedItem> attachments);
    void uploadAttachment(String filePath);
    void saveMessageToDraft(String recepient, String cc, String bcc, String subject, String bodyEmail);
}
