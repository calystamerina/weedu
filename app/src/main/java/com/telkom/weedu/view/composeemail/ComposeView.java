package com.telkom.weedu.view.composeemail;

import com.telkom.weedu.base.IBaseView;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadStatus;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadedItem;

/**
 * Created by Dodi Rivaldi on 05/04/2017.
 */

public interface ComposeView extends IBaseView {
    void showPostMailSuccessMessage(String message);
    void showPostMailFailedMessage(String message);
    void postNewEmail();
    void postReplyEmail();
    void onReplyMailSuccess(String message);
    void onReplyMailFailed(String message);
    void onUploadAttachmentProgress(AttachmentUploadStatus attachmentUploadStatus);
    void onUploadAttachmentSuccess(String filename, AttachmentUploadedItem item);
    void onUploadAttachmentFailed(String filename, String message);
    void onSaveMessageToDraftSuccess(String message);
    void onSaveMessageToDraftFailed(String message);
}
