package com.telkom.weedu.data.api;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.libs.ApiClient;
import com.telkom.weedu.data.api.libs.EdumailApiClient;
import com.telkom.weedu.data.api.libs.QbacaApiClient;
import com.telkom.weedu.data.api.libs.RuangguruApiClient;
import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.edumail.AttachmentUploadedItem;
import com.telkom.weedu.data.api.model.edumail.ResultItem;
import com.telkom.weedu.data.api.model.edumail.SearchResultItem;
import com.telkom.weedu.data.api.model.request.ContactUsRequest;
import com.telkom.weedu.data.api.model.request.LoginRequest;
import com.telkom.weedu.data.api.model.request.MailRequest;
import com.telkom.weedu.data.api.model.request.PostmailRequest;
import com.telkom.weedu.data.api.model.request.RegisterRequest;
import com.telkom.weedu.data.api.model.request.ReplymailRequest;
import com.telkom.weedu.data.api.model.request.ResetPasswordRequest;
import com.telkom.weedu.data.api.model.response.BaseResponse;
import com.telkom.weedu.data.api.model.response.ErrorResponse;
import com.telkom.weedu.data.api.model.response.HistoryResponse;
import com.telkom.weedu.data.api.model.response.LoginResponse;
import com.telkom.weedu.data.api.model.response.ProfileResponse;
import com.telkom.weedu.data.api.model.response.QBacaBookListResponse;
import com.telkom.weedu.data.api.model.response.RegisterResponse;
import com.telkom.weedu.data.api.model.response.edumail.AttachmentUploadedResponse;
import com.telkom.weedu.data.api.model.response.edumail.ForwardResponse;
import com.telkom.weedu.data.api.model.response.edumail.MailDetailResponse;
import com.telkom.weedu.data.api.model.response.edumail.MailResponse;
import com.telkom.weedu.data.api.model.response.edumail.EdumailProfileResponse;
import com.telkom.weedu.data.api.model.response.edumail.ReplyResponse;
import com.telkom.weedu.data.api.model.response.edumail.SearchResultResponse;
import com.telkom.weedu.data.api.model.response.edumail.UnreadMailResponse;
import com.telkom.weedu.data.mapper.edumail.Attachment;
import com.telkom.weedu.data.mapper.edumail.Mail;
import com.telkom.weedu.utils.AppConstants;
import com.telkom.weedu.utils.AppMessages;
import com.telkom.weedu.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import timber.log.Timber;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class ApiManager implements IApiManager {
    private ApiClient mApiClient;
    private RuangguruApiClient mRuangguruApiClient;
    private EdumailApiClient mEdumailApiCLient;
    private QbacaApiClient mQbacaApiClient;
    private OkHttpClient mOkhttpClient;

    @Inject
    public ApiManager(ApiClient mApiClient, RuangguruApiClient mRuangguruApiClient, EdumailApiClient mEdumailApiCLient, QbacaApiClient mQbacaApiClient, OkHttpClient mOkHttpClient) {
        this.mApiClient = mApiClient;
        this.mRuangguruApiClient = mRuangguruApiClient;
        this.mEdumailApiCLient = mEdumailApiCLient;
        this.mQbacaApiClient = mQbacaApiClient;
        this.mOkhttpClient = mOkHttpClient;
    }

    @Override
    public void cancelAllRequest() {
        mOkhttpClient.dispatcher().cancelAll();
    }

    //    region MainApi
    @Override
    public void login(LoginRequest params, final ApiCallback.OnLoginRequestCallback callback) {
        Call<LoginResponse> loginRequest = mApiClient.loginRequest(params);
        loginRequest.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    callback.onLoginRequestSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onLoginRequestFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onLoginRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void register(RegisterRequest request, final ApiCallback.OnRegisterRequestCallback callback) {
        Call<RegisterResponse> registerRequest = mApiClient.registerRequest(request);
        registerRequest.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    callback.onRegisterRequestSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onRegisterRequestFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onRegisterRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void getHistory(String id, String accessToken, final ApiCallback.OnHistoryRequestCallback callback) {
        Call<HistoryResponse> getHistoryRequest = mApiClient.getHistoryRequest(id, accessToken);
        getHistoryRequest.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if (response.isSuccessful()) {
                    callback.onHistoryRequestSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onHistoryRequestFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                callback.onHistoryRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void resetPassword(ResetPasswordRequest request, String id, String accessToken, final ApiCallback.OnResetPasswordCallback callback) {
        Call<BaseResponse> resetPassword = mApiClient.resetPassword(request, id, accessToken);
        resetPassword.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    callback.onResetPasswordSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onResetPasswordFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onResetPasswordFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void editProfile(Profile request, String id, String accessToken, final ApiCallback.OnEditProfileRequestCallback callback) {
        Call<BaseResponse> editProfile = mApiClient.editProfile(request, id, accessToken);
        editProfile.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    callback.onEditRequestSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onEditRequestFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onEditRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void loadProfile(String id, String accessToken, final ApiCallback.OnLoadProfileRequestCallback callback) {
        Call<ProfileResponse> loadProfile = mApiClient.loadProfile(id, accessToken);
        loadProfile.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    callback.onLoadRequestSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onLoadRequestFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                callback.onLoadRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void logout(String accessToken, final ApiCallback.OnLogoutRequestCallback callback) {
        Call<BaseResponse> logout = mApiClient.logout(accessToken);
        logout.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    callback.onLogoutRequestSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onLogoutRequestFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onLogoutRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void contactUs(String id, String accessToken, ContactUsRequest request, final ApiCallback.OnPostContactUsCallback callback) {
        Call<BaseResponse> contactUs = mApiClient.contactUs(id, accessToken, request);
        contactUs.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    callback.onPostContactUsSuccess(response.body());
                } else {
                    try {
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        callback.onPostContactUsFailed(errorResponse.getError().getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callback.onPostContactUsFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }


    //    endregion MainApi


    //    region EdumailApi
    @Override
    public void loadEmail(final MailRequest mailRequest, final ApiCallback.OnGetMailRequestCallback onGetMailRequestCallback) {
        Call<MailResponse> request = mEdumailApiCLient.getMailRequest(mailRequest.getMailType(),
                mailRequest.getPage(), mailRequest.getPerPage(), mailRequest.getEdumailToken());
        request.enqueue(new Callback<MailResponse>() {
            @Override
            public void onResponse(Call<MailResponse> call, Response<MailResponse> response) {
                if (response.isSuccessful()) {
                    MailResponse m = response.body();
                    if (m.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        if (m.getEdumailData().getListResultItems() != null) {
                            if (m.getEdumailData().getListResultItems().size() > 0) {
                                LinkedList<Mail> mails = new LinkedList<>();
                                for (ResultItem item : m.getEdumailData().getListResultItems()) {
                                    Mail mail = Mail.getMail(item);
                                    if (mailRequest.getMailType().equalsIgnoreCase(AppConstants.EDUMAIL_TYPE_TRASH)) {
                                        mail.setTrashed(true);
                                    }
                                    mails.add(mail);

                                }
                                onGetMailRequestCallback.onGetMailRequestSuccess(mails);
                            } else {
                                onGetMailRequestCallback.onGetMailRequestEmpty(AppMessages.ERROR_NO_DATA);
                            }
                        } else {
                            onGetMailRequestCallback.onGetMailRequestEmpty(AppMessages.ERROR_NO_DATA);
                        }
                    } else {
                        if (m.getStatus().equalsIgnoreCase(AppMessages.ERROR_EDUMAIL_EMPTY_DATA)) {
                            onGetMailRequestCallback.onGetMailRequestFailed(AppMessages.ERROR_NO_DATA);
                        } else {
                            onGetMailRequestCallback.onGetMailRequestFailed(m.getStatus());
                        }
                    }
                } else {
                    onGetMailRequestCallback.onGetMailRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<MailResponse> call, Throwable t) {
                onGetMailRequestCallback.onGetMailRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }


    @Override
    public void composeEmail(PostmailRequest postmailRequest, final ApiCallback.OnPostMailRequestCallback onPostMailRequestCallback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", postmailRequest.getToken());
        params.put("to", postmailRequest.getRecepient());
        params.put("subject", postmailRequest.getSubject());
        params.put("body", postmailRequest.getBody());
        params.put("cc", postmailRequest.getCc());
        params.put("bcc", postmailRequest.getBcc());

        if (postmailRequest.getListAttachments() != null){
            if (postmailRequest.getListAttachments().size() > 0){
                for (AttachmentUploadedItem item : postmailRequest.getListAttachments()){
                    params.put("attachments", item.getResult());
                }
            }
        }

        Call<BaseEdumailResponse> request = mEdumailApiCLient.composeRequest(params);
        request.enqueue(new Callback<BaseEdumailResponse>() {
            @Override
            public void onResponse(Call<BaseEdumailResponse> call, Response<BaseEdumailResponse> response) {
                if (response.isSuccessful()) {
                    BaseEdumailResponse b = response.body();
                    if (b.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onPostMailRequestCallback.onPostMailRequestSuccess(b);
                    } else {
                        onPostMailRequestCallback.onPostMailRequestFailed(b.getStatus());
                    }
                } else {
                    onPostMailRequestCallback.onPostMailRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<BaseEdumailResponse> call, Throwable t) {
                onPostMailRequestCallback.onPostMailRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });

    }

    @Override
    public void getUnreadMail(MailRequest mailRequest, final ApiCallback.OnGetUnreadMailRequestCallback onGetUnreadMailRequestCallback) {
        Call<UnreadMailResponse> request = mEdumailApiCLient.getUnreadMailRequest(mailRequest.getEdumailToken());
        request.enqueue(new Callback<UnreadMailResponse>() {
            @Override
            public void onResponse(Call<UnreadMailResponse> call, Response<UnreadMailResponse> response) {
                if (response.isSuccessful()) {
                    UnreadMailResponse unreadMailResponse = response.body();
                    if (unreadMailResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onGetUnreadMailRequestCallback.onGetUnreadSuccess(unreadMailResponse.getData().getUnread());
                    } else {
                        onGetUnreadMailRequestCallback.onGetUnreadFailed(unreadMailResponse.getStatus());
                    }
                } else {
                    onGetUnreadMailRequestCallback.onGetUnreadFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<UnreadMailResponse> call, Throwable t) {
                onGetUnreadMailRequestCallback.onGetUnreadFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void loadMailDetail(MailRequest mailRequest, final ApiCallback.OnGetMailDetailRequestCallback onGetMailDetailRequestCallback) {
        Call<MailDetailResponse> request = mEdumailApiCLient.loadMailDetail(mailRequest.getMailId(), mailRequest.getEdumailToken());
        request.enqueue(new Callback<MailDetailResponse>() {
            @Override
            public void onResponse(Call<MailDetailResponse> call, Response<MailDetailResponse> response) {
                if (response.isSuccessful()) {
                    MailDetailResponse mailDetailResponse = response.body();
                    if (mailDetailResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        Mail mail = Mail.getMail(mailDetailResponse.getData().getResultItem());
                        onGetMailDetailRequestCallback.onGetMailDetailSuccess(mail);
                    } else {
                        onGetMailDetailRequestCallback.onGetMailDetailFailed(mailDetailResponse.getStatus());
                    }
                } else {
                    onGetMailDetailRequestCallback.onGetMailDetailFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<MailDetailResponse> call, Throwable t) {
                onGetMailDetailRequestCallback.onGetMailDetailFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void getReplyPreference(MailRequest mailRequest, final ApiCallback.OnGetReplyReferenceRequestCallback onGetReplyReferenceRequestCallback) {
        Call<ReplyResponse> request = mEdumailApiCLient.getReplyPreference(mailRequest.getMailId(), mailRequest.getEdumailToken());
        request.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {
                if (response.isSuccessful()) {
                    ReplyResponse replyResponse = response.body();
                    if (replyResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onGetReplyReferenceRequestCallback.onGetReplyReferenceRequestSuccess(replyResponse.getReplyData());
                    } else {
                        onGetReplyReferenceRequestCallback.onGetReplyReferenceRequestFailed(replyResponse.getStatus());
                    }
                } else {
                    onGetReplyReferenceRequestCallback.onGetReplyReferenceRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                onGetReplyReferenceRequestCallback.onGetReplyReferenceRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void getReplyAllPreference(MailRequest mailRequest, final ApiCallback.OnGetReplyAllReferenceRequestCallback onGetReplyAllReferenceRequestCallback) {
        Call<ReplyResponse> request = mEdumailApiCLient.getReplyAllPreference(mailRequest.getMailId(), mailRequest.getEdumailToken());
        request.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {
                if (response.isSuccessful()) {
                    ReplyResponse replyResponse = response.body();
                    if (replyResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onGetReplyAllReferenceRequestCallback.onGetReplyAllReferenceRequestSuccess(replyResponse.getReplyData());
                    } else {
                        onGetReplyAllReferenceRequestCallback.onGetReplyAllRefferenceRequestFailed(replyResponse.getStatus());
                    }
                } else {
                    onGetReplyAllReferenceRequestCallback.onGetReplyAllRefferenceRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                onGetReplyAllReferenceRequestCallback.onGetReplyAllRefferenceRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void getForwardPreference(MailRequest mailRequest, final ApiCallback.OnGetForwardReferenceRequestCallback onGetForwardReferenceRequestCallback) {
        Call<ForwardResponse> request = mEdumailApiCLient.getForwardPreference(mailRequest.getMailId(), mailRequest.getEdumailToken());
        request.enqueue(new Callback<ForwardResponse>() {
            @Override
            public void onResponse(Call<ForwardResponse> call, Response<ForwardResponse> response) {
                if (response.isSuccessful()) {
                    ForwardResponse forwardResponse = response.body();
                    if (forwardResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onGetForwardReferenceRequestCallback.onGetForwardReferenceRequestSuccess(forwardResponse.getForwardData());
                    } else {
                        onGetForwardReferenceRequestCallback.onGetForwardReferenceRequestFailed(forwardResponse.getStatus());
                    }
                } else {
                    onGetForwardReferenceRequestCallback.onGetForwardReferenceRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<ForwardResponse> call, Throwable t) {
                onGetForwardReferenceRequestCallback.onGetForwardReferenceRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void postAddRemoveStarInMail(final MailRequest mailRequest, final ApiCallback.OnPostStaringMailRequestCallback onPostStaringMailRequestCallback) {
        Call<BaseEdumailResponse> request = mEdumailApiCLient.postAddRemoveStarMail(mailRequest.getMailId(), mailRequest.getEdumailToken(),
                mailRequest.isStarred());
        request.enqueue(new Callback<BaseEdumailResponse>() {
            @Override
            public void onResponse(Call<BaseEdumailResponse> call, Response<BaseEdumailResponse> response) {
                if (response.isSuccessful()) {
                    BaseEdumailResponse baseEdumailResponse = response.body();
                    if (baseEdumailResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        boolean status = mailRequest.isStarred();
                        Mail mail = mailRequest.getMail();
                        mail.setStarred(status);

                        onPostStaringMailRequestCallback.onPostStaringMailRequestSuccess(baseEdumailResponse, mailRequest.getMail());
                    } else {
                        onPostStaringMailRequestCallback.onPostStaringMailRequestFailed(baseEdumailResponse.getStatus());
                    }
                } else {
                    onPostStaringMailRequestCallback.onPostStaringMailRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<BaseEdumailResponse> call, Throwable t) {
                onPostStaringMailRequestCallback.onPostStaringMailRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void postRemoveMailToTrash(final MailRequest mailRequest, final ApiCallback.OnPostRemoveMailToTrashCallback onPostRemoveMailToTrashCallback) {
        Call<BaseEdumailResponse> request = mEdumailApiCLient.postRemoveEmailToTrash(mailRequest.getMailId(), mailRequest.getEdumailToken(), AppConstants.EDUMAIL_TRASH_DIRECTORY);
        request.enqueue(new Callback<BaseEdumailResponse>() {
            @Override
            public void onResponse(Call<BaseEdumailResponse> call, Response<BaseEdumailResponse> response) {
                if (response.isSuccessful()) {
                    BaseEdumailResponse baseEdumailResponse = response.body();
                    if (baseEdumailResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onPostRemoveMailToTrashCallback.onPostRemoveMailToTrashRequestSuccess(baseEdumailResponse, mailRequest.getMailId());
                    } else {
                        onPostRemoveMailToTrashCallback.onPostRemoveMailToTrashRequestFailed(baseEdumailResponse.getStatus());
                    }
                } else {
                    onPostRemoveMailToTrashCallback.onPostRemoveMailToTrashRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<BaseEdumailResponse> call, Throwable t) {
                onPostRemoveMailToTrashCallback.onPostRemoveMailToTrashRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void postRemoveMailPermanently(final MailRequest mailRequest, final ApiCallback.OnPostRemoveMailPermanentlyRequestCallback onPostRemoveMailPermanentlyRequestCallback) {
        Call<BaseEdumailResponse> request = mEdumailApiCLient.postRemoveEmailPermanently(mailRequest.getMailId(), mailRequest.getEdumailToken());
        request.enqueue(new Callback<BaseEdumailResponse>() {
            @Override
            public void onResponse(Call<BaseEdumailResponse> call, Response<BaseEdumailResponse> response) {
                if (response.isSuccessful()) {
                    BaseEdumailResponse baseEdumailResponse = response.body();
                    if (baseEdumailResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onPostRemoveMailPermanentlyRequestCallback.onPostRemoveMailPermanentlySuccess(baseEdumailResponse, mailRequest.getMailId());
                    } else {
                        onPostRemoveMailPermanentlyRequestCallback.onPostRemoveMailPermanentlyFailed(baseEdumailResponse.getStatus());
                    }
                } else {
                    onPostRemoveMailPermanentlyRequestCallback.onPostRemoveMailPermanentlyFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<BaseEdumailResponse> call, Throwable t) {
                onPostRemoveMailPermanentlyRequestCallback.onPostRemoveMailPermanentlyFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void postReplyMail(ReplymailRequest replymailRequest, final ApiCallback.OnPostReplyMailRequestCallback onPostReplyMailRequestCallback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", replymailRequest.getToken());
        params.put("replyto", replymailRequest.getReplyTo());
        params.put("to", replymailRequest.getRecepient());
        params.put("references", replymailRequest.getReferences());
        params.put("subject", replymailRequest.getSubject());
        params.put("body", replymailRequest.getBody());
        params.put("cc", replymailRequest.getCc());
        params.put("bcc", replymailRequest.getBcc());

        if (replymailRequest.getListAttachments() != null){
            if (replymailRequest.getListAttachments().size() > 0){
                for (AttachmentUploadedItem item : replymailRequest.getListAttachments()){
                    params.put("attachments", item.getResult());
                }
            }
        }

        Call<BaseEdumailResponse> request = mEdumailApiCLient.postReplyMail(params);
        request.enqueue(new Callback<BaseEdumailResponse>() {
            @Override
            public void onResponse(Call<BaseEdumailResponse> call, Response<BaseEdumailResponse> response) {
                if (response.isSuccessful()) {
                    BaseEdumailResponse baseEdumailResponse = response.body();
                    if (baseEdumailResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onPostReplyMailRequestCallback.onPostReplyMailRequestSuccess(baseEdumailResponse.getStatus());
                    } else {
                        onPostReplyMailRequestCallback.onPostReplyMailRequestFailed(baseEdumailResponse.getStatus());
                    }
                } else {
                    onPostReplyMailRequestCallback.onPostReplyMailRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<BaseEdumailResponse> call, Throwable t) {
                onPostReplyMailRequestCallback.onPostReplyMailRequestSuccess(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void getProfile(MailRequest mailRequest, final ApiCallback.OnGetEdumailProfileCallback onGetProfileCallback) {
        Call<EdumailProfileResponse> request = mEdumailApiCLient.getProfile(mailRequest.getEdumailToken());
        request.enqueue(new Callback<EdumailProfileResponse>() {
            @Override
            public void onResponse(Call<EdumailProfileResponse> call, Response<EdumailProfileResponse> response) {
                if (response.isSuccessful()) {
                    EdumailProfileResponse p = response.body();
                    if (p.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        onGetProfileCallback.onGetProfileRequestSuccess(p.getData().getUserDetail());
                    } else {
                        onGetProfileCallback.onGetProfileRequestFailed(p.getStatus());
                    }
                } else {
                    onGetProfileCallback.onGetProfileRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<EdumailProfileResponse> call, Throwable t) {
                onGetProfileCallback.onGetProfileRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void postUploadAttachment(MailRequest mailRequest, String filePath, final ApiCallback.OnPostUploadAttachmentCallback onPostUploadAttachmentCallback) {
        File file = new File(filePath);
        final String filename = file.getName();
        RequestBody reqFile = RequestBody.create(MediaType.parse("file/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RequestBody token = RequestBody.create(MediaType.parse("text/plain"), mailRequest.getEdumailToken());

        Call<AttachmentUploadedResponse> request = mEdumailApiCLient.postUploadAttachment(body, token);
        request.enqueue(new Callback<AttachmentUploadedResponse>() {
            @Override
            public void onResponse(Call<AttachmentUploadedResponse> call, Response<AttachmentUploadedResponse> response) {
                if (response.isSuccessful()){
                    AttachmentUploadedResponse attachmentUploadedResponse = response.body();
                    if (attachmentUploadedResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS){
                        onPostUploadAttachmentCallback.onPostUploadAttachmentSuccess(filename, attachmentUploadedResponse);
                    }else {
                        onPostUploadAttachmentCallback.onPostUploadAttachmentFailed(filename, attachmentUploadedResponse.getStatus());
                    }
                }else{
                    onPostUploadAttachmentCallback.onPostUploadAttachmentFailed(filename, AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<AttachmentUploadedResponse> call, Throwable t) {
                onPostUploadAttachmentCallback.onPostUploadAttachmentFailed(filename, AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void postSearchMail(MailRequest mailRequest, final ApiCallback.OnPostSearchMailRequestCallback onPostSearchMailRequestCallback) {
        Call<SearchResultResponse> request = mEdumailApiCLient.postSearchMail(mailRequest.getKey(),
                mailRequest.getPage(), mailRequest.getPerPage(), mailRequest.getEdumailToken());
        request.enqueue(new Callback<SearchResultResponse>() {
            @Override
            public void onResponse(Call<SearchResultResponse> call, Response<SearchResultResponse> response) {
                if (response.isSuccessful()) {
                    SearchResultResponse m = response.body();
                    if (m.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS) {
                        if (m.getData().getListResult() != null) {
                            if (m.getData().getListResult().size() > 0) {
                                LinkedList<Mail> mails = new LinkedList<>();
                                for (SearchResultItem item : m.getData().getListResult()) {
                                    Mail mail = Mail.getMail(item);
                                    mails.add(mail);
                                }
                                onPostSearchMailRequestCallback.onPostSearchRequestSuccess(mails);
                            } else {
                                onPostSearchMailRequestCallback.onPostSearchRequestEmpty(AppMessages.ERROR_NO_DATA);
                            }
                        } else {
                            onPostSearchMailRequestCallback.onPostSearchRequestEmpty(AppMessages.ERROR_NO_DATA);
                        }
                    } else {
                        onPostSearchMailRequestCallback.onPostSearchRequestFailed(m.getStatus());
                    }
                } else {
                    onPostSearchMailRequestCallback.onPostSearchRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<SearchResultResponse> call, Throwable t) {
                onPostSearchMailRequestCallback.onPostSearchRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void postDraft(PostmailRequest postmailRequest, final ApiCallback.OnPostDraftRequestCallback onPostDraftRequestCallback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", postmailRequest.getToken());
        params.put("to", postmailRequest.getRecepient());
        params.put("subject", postmailRequest.getSubject());
        params.put("body", postmailRequest.getBody());
        params.put("type", "okdrafts");

        Call<BaseEdumailResponse> request = mEdumailApiCLient.postDraftRequest(params);
        request.enqueue(new Callback<BaseEdumailResponse>() {
            @Override
            public void onResponse(Call<BaseEdumailResponse> call, Response<BaseEdumailResponse> response) {
                if (response.isSuccessful()){
                    BaseEdumailResponse baseEdumailResponse = response.body();
                    if (baseEdumailResponse.getCode() == AppConstants.EDUMAIL_API_STATUS_CODE_SUCCESS){
                        onPostDraftRequestCallback.onPostDraftReqeustSuccess(baseEdumailResponse);
                    }else{
                        onPostDraftRequestCallback.onPostDraftRequestFailed(baseEdumailResponse.getStatus());
                    }
                }else{
                    onPostDraftRequestCallback.onPostDraftRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<BaseEdumailResponse> call, Throwable t) {
                onPostDraftRequestCallback.onPostDraftRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    @Override
    public void getAttachmentFile(final Attachment attachment, final ApiCallback.OnDownloadAttachmentCallback callback) {
        Call<ResponseBody> request = mEdumailApiCLient.downloadAttachmentFile(attachment.getUrl());
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        String filepath = FileUtils.writeResponseBodyToDisk(response.body(), attachment);
                        if(!TextUtils.isEmpty(filepath)){
                            callback.onDownloadSuccess(attachment, filepath);
                        }else{
                            callback.onDownloadFailed(attachment, AppMessages.ERROR_INVALID_RESPONSE);
                        }
                    }else{
                        callback.onDownloadFailed(attachment, AppMessages.ERROR_INVALID_RESPONSE);
                    }
                }else{
                    callback.onDownloadFailed(attachment, AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onDownloadFailed(attachment, AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }

    //    endregion EdumailApi


    //    region QbacaApi
    @Override
    public void getQBacaData(final ApiCallback.OnQBacaRequestCallback callback) {
        Call<QBacaBookListResponse> qbacaRequest = mQbacaApiClient.getQBacaTopDownloads(0, 10, 0, 2);
        Timber.d("NUM OF CALLS" + mOkhttpClient.dispatcher().queuedCalls().size());
        qbacaRequest.enqueue(new Callback<QBacaBookListResponse>() {
            @Override
            public void onResponse(Call<QBacaBookListResponse> call, Response<QBacaBookListResponse> response) {
                if (response.isSuccessful()) {
                    QBacaBookListResponse q = response.body();
                    if (q != null) {
                        if (q.getStatus().equalsIgnoreCase("1")) {
                            if (q.getListData().size() > 0) {
                                callback.onQBacaRequestSuccess(q.getListData());
                            } else {
                                callback.onQBacaRequestFailed(AppMessages.ERROR_NO_DATA);
                            }
                        } else {
                            callback.onQBacaRequestFailed(AppMessages.ERROR_NO_DATA);
                        }
                    } else {
                        callback.onQBacaRequestFailed(AppMessages.ERROR_INVALID_RESPONSE);
                    }
                } else {
                    callback.onQBacaRequestFailed(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
                }
            }

            @Override
            public void onFailure(Call<QBacaBookListResponse> call, Throwable t) {
                callback.onQBacaRequestEmpty(AppMessages.ERROR_UNABLE_TO_CONNECT_SERVER);
            }
        });
    }


    //    endregion QbacaApi


    //    region RuangguruApi


    //    endregion RuangguruApi

}
