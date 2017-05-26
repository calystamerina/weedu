package com.telkom.weedu.data.api.libs;

import com.telkom.weedu.data.api.model.Profile;
import com.telkom.weedu.data.api.model.request.ContactUsRequest;
import com.telkom.weedu.data.api.model.request.LoginRequest;
import com.telkom.weedu.data.api.model.request.RegisterRequest;
import com.telkom.weedu.data.api.model.request.ResetPasswordRequest;
import com.telkom.weedu.data.api.model.response.BaseResponse;
import com.telkom.weedu.data.api.model.response.HistoryResponse;
import com.telkom.weedu.data.api.model.response.LoginResponse;
import com.telkom.weedu.data.api.model.response.ProfileResponse;
import com.telkom.weedu.data.api.model.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose: put all api calls here
 */

public interface ApiClient {

    // TODO: 3/3/17 change this as desired request

    @POST("users/login")
    Call<LoginResponse> loginRequest(@Body LoginRequest loginRequest);


    @POST("users/logout")
    Call<BaseResponse> logout(@Query("access_token") String accessToken);

    @POST("users/register")
    Call<RegisterResponse> registerRequest(@Body RegisterRequest loginRequest);

    @GET("users/{id}/history")
    Call<HistoryResponse> getHistoryRequest(@Path("id") String id, @Query("access_token") String accessToken);


    @POST("users/{id}/change-password")
    Call<BaseResponse> resetPassword(@Body ResetPasswordRequest request, @Path("id") String id, @Query("access_token") String accessToken);

    @PUT("users/{id}/profile")
    Call<BaseResponse> editProfile(@Body Profile request, @Path("id") String id, @Query("access_token") String accessToken);

    @GET("users/{id}/profile")
    Call<ProfileResponse> loadProfile(@Path("id") String id, @Query("access_token") String accessToken);

    @POST("users/{id}/complaint")
    Call<BaseResponse> contactUs(@Path("id") String id, @Query("access_token") String accessToken, @Body ContactUsRequest request);


}
