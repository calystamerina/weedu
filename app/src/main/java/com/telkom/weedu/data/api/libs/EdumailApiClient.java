package com.telkom.weedu.data.api.libs;

import com.telkom.weedu.base.response.BaseEdumailResponse;
import com.telkom.weedu.data.api.model.response.LoginResponse;
import com.telkom.weedu.data.api.model.response.edumail.AttachmentUploadedResponse;
import com.telkom.weedu.data.api.model.response.edumail.ForwardResponse;
import com.telkom.weedu.data.api.model.response.edumail.MailDetailResponse;
import com.telkom.weedu.data.api.model.response.edumail.MailResponse;
import com.telkom.weedu.data.api.model.response.edumail.EdumailProfileResponse;
import com.telkom.weedu.data.api.model.response.edumail.ReplyResponse;
import com.telkom.weedu.data.api.model.response.edumail.SearchResultResponse;
import com.telkom.weedu.data.api.model.response.edumail.UnreadMailResponse;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose: put all api calls here
 */

public interface EdumailApiClient {

    // TODO: 3/3/17 change this as desired request
    @GET("/login")
    Call<LoginResponse> loginRequest(@Path("username") String username
            , @Path("password") String password);

    @FormUrlEncoded
    @POST("/mailsmobile")
    Call<MailResponse> getMailRequest (@Field("q") String q, @Field("p") int p,
                                    @Field("l") int i, @Field(value = "token", encoded = true) String token);

    @FormUrlEncoded
    @POST("/composemobile")
    Call<BaseEdumailResponse> composeRequest (@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("/apiunread")
    Call<UnreadMailResponse> getUnreadMailRequest(@Field(value = "token", encoded = true) String token);

    @FormUrlEncoded
    @POST("/mailviewmobile")
    Call<MailDetailResponse> loadMailDetail(@Field("id") String mailId, @Field(value = "token", encoded = true) String token);

    @FormUrlEncoded
    @POST("/ajaxreplymobile")
    Call<ReplyResponse> getReplyPreference(@Field("id") String mailId, @Field(value = "token", encoded = true) String token);

    @FormUrlEncoded
    @POST("/ajaxreplyallmobile")
    Call<ReplyResponse> getReplyAllPreference(@Field("id") String mailId, @Field(value = "token", encoded = true) String token);

    @FormUrlEncoded
    @POST("/ajaxfwdmobile")
    Call<ForwardResponse> getForwardPreference(@Field("id") String mailId, @Field(value = "token", encoded = true) String token);

    @FormUrlEncoded
    @POST("/starringmobile")
    Call<BaseEdumailResponse> postAddRemoveStarMail(@Field("id") String mailId, @Field(value = "token", encoded = true) String token,
                                                    @Field("state") boolean isStarred);
    @FormUrlEncoded
    @POST("/trashmobile")
    Call<BaseEdumailResponse> postRemoveEmailToTrash(@Field("ids") String mailId, @Field(value = "token", encoded = true) String token,
                                                    @Field("chto") String trash);
    @FormUrlEncoded
    @POST("/deletepermanen")
    Call<BaseEdumailResponse> postRemoveEmailPermanently(@Field("ids") String mailId, @Field(value = "token", encoded = true) String token);

    @FormUrlEncoded
    @POST("/replymobile")
    Call<BaseEdumailResponse> postReplyMail(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("/apidetailprofile")
    Call<EdumailProfileResponse> getProfile(@Field(value = "token", encoded = true) String token);

    @Multipart
    @POST("/ajaxuploadmobile")
    Call<AttachmentUploadedResponse> postUploadAttachment(@Part MultipartBody.Part file, @Part("token") RequestBody token);

    @FormUrlEncoded
    @POST("/mailsearchmobile")
    Call<SearchResultResponse> postSearchMail (@Field("key") String q, @Field("p") int p,
                                               @Field("l") int i, @Field(value = "token", encoded = true) String token);
    @FormUrlEncoded
    @POST("/draftmails")
    Call<BaseEdumailResponse> postDraftRequest (@FieldMap HashMap<String, String> params);

    @GET
    Call<ResponseBody> downloadAttachmentFile(@Url String fileUrl);

}
