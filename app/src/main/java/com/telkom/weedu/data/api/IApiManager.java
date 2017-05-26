package com.telkom.weedu.data.api;

import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.request.ContactUsRequest;
import com.telkom.weedu.data.api.model.request.LoginRequest;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.api.model.request.PostmailRequest;
import com.telkom.weedu.data.api.model.request.RegisterRequest;
import com.telkom.weedu.data.api.model.request.ReplymailRequest;
import com.telkom.weedu.data.api.model.request.ResetPasswordRequest;
import com.telkom.weedu.data.mapper.edumail.Attachment;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose : interface for ApiManager put all request method here,
 * all method must be the reflection of ApiClient
 */

public interface IApiManager {

    void cancelAllRequest();

    //    region MainApiClient

    void login(LoginRequest loginRequest, ApiCallback.OnLoginRequestCallback onLoginRequestCallback);

    void logout(String accessToken, ApiCallback.OnLogoutRequestCallback callback);

    void register(RegisterRequest registerRequest, ApiCallback.OnRegisterRequestCallback callback);

    void getHistory(String id, String accessToken, ApiCallback.OnHistoryRequestCallback callback);

    void resetPassword(ResetPasswordRequest request, String id, String accessToken, ApiCallback.OnResetPasswordCallback callback);

    void editProfile(Profile request, String id, String accessToken, ApiCallback.OnEditProfileRequestCallback callback);

    void loadProfile(String id, String accessToken, ApiCallback.OnLoadProfileRequestCallback callback);

    void contactUs(String id, String accessToken, ContactUsRequest request, ApiCallback.OnPostContactUsCallback callback);
    //    endregion MainApiClient


    //    region EduMailApiClient

    void loadEmail(MailRequest mailRequest, ApiCallback.OnGetMailRequestCallback onGetMailRequestCallback);

    void composeEmail(PostmailRequest postmailRequest, ApiCallback.OnPostMailRequestCallback onPostMailRequestCallback);

    void getUnreadMail(MailRequest mailRequest, ApiCallback.OnGetUnreadMailRequestCallback onGetUnreadMailRequestCallback);

    void loadMailDetail(MailRequest mailRequest, ApiCallback.OnGetMailDetailRequestCallback onGetMailDetailRequestCallback);

    void getReplyPreference(MailRequest mailRequest, ApiCallback.OnGetReplyReferenceRequestCallback onGetReplyReferenceRequestCallback);

    void getReplyAllPreference(MailRequest mailRequest, ApiCallback.OnGetReplyAllReferenceRequestCallback onGetReplyAllReferenceRequestCallback);

    void getForwardPreference(MailRequest mailRequest, ApiCallback.OnGetForwardReferenceRequestCallback onGetForwardReferenceRequestCallback);

    void postAddRemoveStarInMail(MailRequest mailRequest, ApiCallback.OnPostStaringMailRequestCallback onPostStaringMailRequestCallback);

    void postRemoveMailToTrash(MailRequest mailRequest, ApiCallback.OnPostRemoveMailToTrashCallback onPostRemoveMailToTrashCallback);

    void postRemoveMailPermanently(MailRequest mailRequest, ApiCallback.OnPostRemoveMailPermanentlyRequestCallback onPostRemoveMailPermanentlyRequestCallback);

    void postReplyMail(ReplymailRequest replymailRequest, ApiCallback.OnPostReplyMailRequestCallback onPostReplyMailRequestCallback);

    void getProfile(MailRequest mailRequest, ApiCallback.OnGetEdumailProfileCallback onGetProfileCallback);

    void postUploadAttachment(MailRequest mailRequest, String filePath, ApiCallback.OnPostUploadAttachmentCallback onPostUploadAttachmentCallback);

    void postSearchMail(MailRequest mailRequest, ApiCallback.OnPostSearchMailRequestCallback onPostSearchMailRequestCallback);

    void postDraft(PostmailRequest postmailRequest, ApiCallback.OnPostDraftRequestCallback onPostDraftRequestCallback);

    void getAttachmentFile(Attachment attachment, ApiCallback.OnDownloadAttachmentCallback onDownloadAttachmentCallback);
    //    endregion EduMailApiClient


    //    region QbacaApiClient

    void getQBacaData(ApiCallback.OnQBacaRequestCallback callback);


    //    endregion QbacaApiClient


    //    region RuangguruApiClient


    //    endregion RuangguruApiClient
}
