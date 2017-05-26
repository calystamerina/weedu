package com.telkom.weedu.data.api.libs;

import com.telkom.weedu.data.api.model.response.LoginResponse;
import com.telkom.weedu.data.api.model.response.QBacaBookListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose: put all api calls here
 */

public interface QbacaApiClient {
    // TODO: 3/3/17 change this as desired request

    @GET("/login")
    Call<LoginResponse> loginRequest(@Path("username") String username
            , @Path("password") String password);

    @GET("listByTopDownload/{start}/{limit}/{category}/{language}")
    Call<QBacaBookListResponse> getQBacaTopDownloads(@Path("start") int start,
                                                     @Path("limit") int limit,
                                                     @Path("category") int category,
                                                     @Path("language") int language);
}
