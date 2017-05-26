package com.telkom.weedu.data;

import com.telkom.weedu.data.api.ApiCallback;
import com.telkom.weedu.data.api.IApiManager;
import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.User;
import com.telkom.weedu.data.api.model.request.ContactUsRequest;
import com.telkom.weedu.data.api.model.request.LoginRequest;
import com.telkom.weedu.data.api.model.request.RegisterRequest;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.api.model.request.PostmailRequest;
import com.telkom.weedu.data.api.model.request.ReplymailRequest;
import com.telkom.weedu.data.api.model.request.ResetPasswordRequest;
import com.telkom.weedu.data.db.IDbManager;
import com.telkom.weedu.data.mapper.edumail.Attachment;
import com.telkom.weedu.data.preference.IPreferenceManager;

import javax.inject.Inject;

/**
 * Created by ghiyatshanif on 2/20/17.
 * purpose : Class that handles all database request, delegate all method to each corresponding helpers
 */

public class DataManager implements IDataManager {
    private final IApiManager mApiManager;
    private final IPreferenceManager mPreferenceManager;
    private final IDbManager mDbManager;


    @Inject
    public DataManager(IApiManager mApiManager, IPreferenceManager mPreferenceManager, IDbManager mDbManager) {
        this.mApiManager = mApiManager;
        this.mPreferenceManager = mPreferenceManager;
        this.mDbManager = mDbManager;
    }

    @Override
    public void cancelAllRequest() {
        mApiManager.cancelAllRequest();
    }

    @Override
    public void saveUserSession(String accessToken, String username) {

    }

    ///////////////////////////////////////////////////////////////////////////
    // API DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////

    // region MainApi
    @Override
    public void login(LoginRequest loginRequest, ApiCallback.OnLoginRequestCallback callback) {
        mApiManager.login(loginRequest, callback);
    }

    @Override
    public void register(RegisterRequest registerRequest, ApiCallback.OnRegisterRequestCallback callback) {
        mApiManager.register(registerRequest, callback);
    }

    @Override
    public void editProfile(Profile request, String id, String accessToken, ApiCallback.OnEditProfileRequestCallback callback) {
        mApiManager.editProfile(request, id, accessToken, callback);
    }

    @Override
    public void getHistory(String id, String accessToken, ApiCallback.OnHistoryRequestCallback callback) {
        mApiManager.getHistory(id, accessToken, callback);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request, String id, String accessToken, ApiCallback.OnResetPasswordCallback callback) {
        mApiManager.resetPassword(request, id, accessToken, callback);
    }

    @Override
    public void loadProfile(String id, String accessToken, ApiCallback.OnLoadProfileRequestCallback callback) {
        mApiManager.loadProfile(id, accessToken, callback);
    }

    @Override
    public void logout(String accessToken, ApiCallback.OnLogoutRequestCallback callback) {
        mApiManager.logout(accessToken, callback);
    }

    @Override
    public void contactUs(String id, String accessToken, ContactUsRequest request, ApiCallback.OnPostContactUsCallback callback) {
        mApiManager.contactUs(id, accessToken, request, callback);
    }

    // endregion


    // region EduMailApi
    @Override
    public void loadEmail(MailRequest mailRequest, ApiCallback.OnGetMailRequestCallback onGetMailRequestCallback) {
        mApiManager.loadEmail(mailRequest, onGetMailRequestCallback);
    }

    @Override
    public void composeEmail(PostmailRequest postmailRequest, ApiCallback.OnPostMailRequestCallback onPostMailRequestCallback) {
        mApiManager.composeEmail(postmailRequest, onPostMailRequestCallback);
    }

    @Override
    public void getUnreadMail(MailRequest mailRequest, ApiCallback.OnGetUnreadMailRequestCallback onGetUnreadMailRequestCallback) {
        mApiManager.getUnreadMail(mailRequest, onGetUnreadMailRequestCallback);
    }

    @Override
    public void loadMailDetail(MailRequest mailRequest, ApiCallback.OnGetMailDetailRequestCallback onGetMailDetailRequestCallback) {
        mApiManager.loadMailDetail(mailRequest, onGetMailDetailRequestCallback);
    }

    @Override
    public void getReplyPreference(MailRequest mailRequest, ApiCallback.OnGetReplyReferenceRequestCallback onGetReplyReferenceRequestCallback) {
        mApiManager.getReplyPreference(mailRequest, onGetReplyReferenceRequestCallback);
    }

    @Override
    public void getReplyAllPreference(MailRequest mailRequest, ApiCallback.OnGetReplyAllReferenceRequestCallback onGetReplyAllReferenceRequestCallback) {
        mApiManager.getReplyAllPreference(mailRequest, onGetReplyAllReferenceRequestCallback);
    }

    @Override
    public void getForwardPreference(MailRequest mailRequest, ApiCallback.OnGetForwardReferenceRequestCallback onGetForwardReferenceRequestCallback) {
        mApiManager.getForwardPreference(mailRequest, onGetForwardReferenceRequestCallback);
    }

    @Override
    public void postAddRemoveStarInMail(MailRequest mailRequest, ApiCallback.OnPostStaringMailRequestCallback onPostStaringMailRequestCallback) {
        mApiManager.postAddRemoveStarInMail(mailRequest, onPostStaringMailRequestCallback);
    }

    @Override
    public void postRemoveMailToTrash(MailRequest mailRequest, ApiCallback.OnPostRemoveMailToTrashCallback onPostRemoveMailToTrashCallback) {
        mApiManager.postRemoveMailToTrash(mailRequest, onPostRemoveMailToTrashCallback);
    }

    @Override
    public void postRemoveMailPermanently(MailRequest mailRequest, ApiCallback.OnPostRemoveMailPermanentlyRequestCallback onPostRemoveMailPermanentlyRequestCallback) {
        mApiManager.postRemoveMailPermanently(mailRequest, onPostRemoveMailPermanentlyRequestCallback);
    }

    @Override
    public void postReplyMail(ReplymailRequest replymailRequest, ApiCallback.OnPostReplyMailRequestCallback onPostReplyMailRequestCallback) {
        mApiManager.postReplyMail(replymailRequest, onPostReplyMailRequestCallback);
    }

    @Override
    public void getProfile(MailRequest mailRequest, ApiCallback.OnGetEdumailProfileCallback onGetProfileCallback) {
        mApiManager.getProfile(mailRequest, onGetProfileCallback);
    }

    @Override
    public void postUploadAttachment(MailRequest mailRequest, String filePath, ApiCallback.OnPostUploadAttachmentCallback onPostUploadAttachmentCallback) {
        mApiManager.postUploadAttachment(mailRequest, filePath, onPostUploadAttachmentCallback);
    }

    @Override
    public void postSearchMail(MailRequest mailRequest, ApiCallback.OnPostSearchMailRequestCallback onPostSearchMailRequestCallback) {
        mApiManager.postSearchMail(mailRequest, onPostSearchMailRequestCallback);
    }

    @Override
    public void postDraft(PostmailRequest postmailRequest, ApiCallback.OnPostDraftRequestCallback onPostDraftRequestCallback) {
        mApiManager.postDraft(postmailRequest, onPostDraftRequestCallback);
    }

    @Override
    public void getAttachmentFile(Attachment attachment, ApiCallback.OnDownloadAttachmentCallback onDownloadAttachmentCallback) {
        mApiManager.getAttachmentFile(attachment, onDownloadAttachmentCallback);
    }

    // endregion

    // region QbacaApi

    @Override
    public void getQBacaData(ApiCallback.OnQBacaRequestCallback callback) {
        mApiManager.getQBacaData(callback);
    }

    // endregion

    // region RuangguruApi


    // endregion

    ///////////////////////////////////////////////////////////////////////////
    // END OF API DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////
    // PREFERENCE DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void saveUserPreference(User user) {
        mPreferenceManager.saveUserPreference(user);
    }

    @Override
    public User getUserPreference() {
        return mPreferenceManager.getUserPreference();
    }

    @Override
    public void setFirstTime(boolean firstTime) {
        mPreferenceManager.setFirstTime(firstTime);
    }

    @Override
    public boolean isFirstTime() {
        return mPreferenceManager.isFirstTime();
    }

    @Override
    public void clearUserPreference() {
        mPreferenceManager.clearUserPreference();
    }

    @Override
    public Profile getUserProfile() {
        return mPreferenceManager.getUserProfile();
    }

    @Override
    public void updateProfile(Profile profile) {
        mPreferenceManager.updateProfile(profile);
    }

    ///////////////////////////////////////////////////////////////////////////
    // END OF PREFERENCE DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////
    // DB DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////
    // END OF DB DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
}
