package com.telkom.weedu.data.api;

import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.QBacaBook;
import com.telkom.weedu.data.api.model.edumail.ForwardData;
import com.telkom.weedu.data.api.model.edumail.ReplyData;
import com.telkom.weedu.data.api.model.edumail.UserDetail;
import com.telkom.weedu.data.api.model.response.BaseResponse;
import com.telkom.weedu.data.api.model.response.HistoryResponse;
import com.telkom.weedu.data.api.model.response.LoginResponse;
import com.telkom.weedu.data.api.model.response.ProfileResponse;
import com.telkom.weedu.data.api.model.response.RegisterResponse;
import com.telkom.weedu.data.api.model.response.edumail.AttachmentUploadedResponse;
import com.telkom.weedu.data.mapper.edumail.Attachment;
import com.telkom.weedu.data.mapper.edumail.Mail;

import java.util.LinkedList;

import okhttp3.ResponseBody;

/**
 * Created by sidiqpermana on 4/5/17.
 */

public interface ApiCallback {

    interface OnLoginRequestCallback {
        void onLoginRequestSuccess(LoginResponse loginResponse);

        void onLoginRequestFailed(String message);
    }

    interface OnLogoutRequestCallback {
        void onLogoutRequestSuccess(BaseResponse response);

        void onLogoutRequestFailed(String message);
    }

    interface OnEditProfileRequestCallback {
        void onEditRequestSuccess(BaseResponse response);

        void onEditRequestFailed(String message);
    }

    interface OnLoadProfileRequestCallback {
        void onLoadRequestSuccess(ProfileResponse profile);

        void onLoadRequestFailed(String message);
    }

    interface OnRegisterRequestCallback {
        void onRegisterRequestSuccess(RegisterResponse registerResponse);

        void onRegisterRequestFailed(String message);
    }

    interface OnHistoryRequestCallback {
        void onHistoryRequestSuccess(HistoryResponse historyResponse);

        void onHistoryRequestFailed(String message);
    }

    interface OnResetPasswordCallback {
        void onResetPasswordSuccess(BaseResponse baseResponse);

        void onResetPasswordFailed(String message);
    }

    interface OnQBacaRequestCallback {
        void onQBacaRequestSuccess(LinkedList<QBacaBook> list);

        void onQBacaRequestFailed(String message);

        void onQBacaRequestEmpty(String message);
    }

    interface OnGetMailRequestCallback {
        void onGetMailRequestSuccess(LinkedList<Mail> list);

        void onGetMailRequestFailed(String message);

        void onGetMailRequestEmpty(String message);
    }

    interface OnPostMailRequestCallback {
        void onPostMailRequestSuccess(BaseEdumailResponse baseEdumailResponse);

        void onPostMailRequestFailed(String message);
    }

    interface OnGetUnreadMailRequestCallback {
        void onGetUnreadSuccess(int count);

        void onGetUnreadFailed(String message);
    }

    interface OnGetMailDetailRequestCallback {
        void onGetMailDetailSuccess(Mail mail);

        void onGetMailDetailFailed(String errorMessage);
    }

    interface OnGetReplyReferenceRequestCallback {
        void onGetReplyReferenceRequestSuccess(ReplyData replyData);

        void onGetReplyReferenceRequestFailed(String message);
    }

    interface OnGetReplyAllReferenceRequestCallback {
        void onGetReplyAllReferenceRequestSuccess(ReplyData replyData);

        void onGetReplyAllRefferenceRequestFailed(String message);
    }

    interface OnGetForwardReferenceRequestCallback {
        void onGetForwardReferenceRequestSuccess(ForwardData forwardData);

        void onGetForwardReferenceRequestFailed(String message);
    }

    interface OnPostStaringMailRequestCallback {
        void onPostStaringMailRequestSuccess(BaseEdumailResponse baseEdumailResponse, Mail mail);

        void onPostStaringMailRequestFailed(String message);
    }

    interface OnPostRemoveMailToTrashCallback {
        void onPostRemoveMailToTrashRequestSuccess(BaseEdumailResponse baseEdumailResponse, String mailId);

        void onPostRemoveMailToTrashRequestFailed(String message);
    }

    interface OnPostRemoveMailPermanentlyRequestCallback {
        void onPostRemoveMailPermanentlySuccess(BaseEdumailResponse baseEdumailResponse, String mailId);

        void onPostRemoveMailPermanentlyFailed(String message);
    }

    interface OnPostReplyMailRequestCallback {
        void onPostReplyMailRequestSuccess(String message);

        void onPostReplyMailRequestFailed(String message);
    }

    interface OnGetEdumailProfileCallback {
        void onGetProfileRequestSuccess(UserDetail userDetail);

        void onGetProfileRequestFailed(String message);
    }

    interface OnPostUploadAttachmentCallback{
        void onPostUploadAttachmentSuccess(String filename, AttachmentUploadedResponse attachmentUploadedResponse);

        void onPostUploadAttachmentFailed(String filename, String message);
    }

    interface OnPostSearchMailRequestCallback{
        void onPostSearchRequestSuccess(LinkedList<Mail> mails);

        void onPostSearchRequestFailed(String message);

        void onPostSearchRequestEmpty(String message);
    }

    interface OnPostDraftRequestCallback {
        void onPostDraftReqeustSuccess(BaseEdumailResponse baseEdumailResponse);

        void onPostDraftRequestFailed(String message);
    }

    interface OnPostContactUsCallback {
        void onPostContactUsSuccess(BaseResponse loginResponse);

        void onPostContactUsFailed(String message);
    }

    interface OnDownloadAttachmentCallback{
        void onDownloadSuccess(Attachment attachment, String downloadedFile);

        void onDownloadFailed(Attachment attachment, String message);
    }
}
