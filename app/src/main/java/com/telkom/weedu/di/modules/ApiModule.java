package com.telkom.weedu.di.modules;

import com.telkom.weedu.BuildConfig;
import com.telkom.weedu.data.api.ApiManager;
import com.telkom.weedu.data.api.IApiManager;
import com.telkom.weedu.data.api.libs.ApiClient;
import com.telkom.weedu.data.api.libs.ApiService;
import com.telkom.weedu.data.api.libs.EdumailApiClient;
import com.telkom.weedu.data.api.libs.HeaderInterceptor;
import com.telkom.weedu.data.api.libs.OkHttpClientFactory;
import com.telkom.weedu.data.api.libs.ParameterInterceptor;
import com.telkom.weedu.data.api.libs.QbacaApiClient;
import com.telkom.weedu.data.api.libs.RuangguruApiClient;
import com.telkom.weedu.di.EdumailApi;
import com.telkom.weedu.di.MainApi;
import com.telkom.weedu.di.QbacaApi;
import com.telkom.weedu.di.RuangguruApi;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Module
public class ApiModule {

    @Provides
    HeaderInterceptor provideHeaderInterceptor() {
//        provide default header (if needed) to hashmap
        HashMap<String, String> headers = new HashMap<>();

        return new HeaderInterceptor(headers);
    }

    @Provides
    ParameterInterceptor provideParameterInterceptor() {
//        put all default parameter like apikey to hashmap
        HashMap<String, String> params = new HashMap<>();
        return new ParameterInterceptor(params);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkhttpClient(HeaderInterceptor headerInterceptor, ParameterInterceptor parameterInterceptor) {
        return OkHttpClientFactory.create(headerInterceptor, parameterInterceptor);
    }

    @MainApi
    @Provides
    String provideMainApiURL() {
        return BuildConfig.BASE_URL;
    }

    @QbacaApi
    @Provides
    String provideQbacaURL() {
        return BuildConfig.QBACA_URL;
    }

    @RuangguruApi
    @Provides
    String provideRuangguruURL() {
        return BuildConfig.RUANGGURU_URL;
    }

    @EdumailApi
    @Provides
    String provideEdumailURL() {
        return BuildConfig.EDUMAIL_URL;
    }

    @Singleton
    @Provides
    ApiClient provideApiClient(OkHttpClient okHttpClient, @MainApi String baseUrl) {
        return ApiService.createService(ApiClient.class, okHttpClient, baseUrl);
    }

    @Singleton
    @Provides
    EdumailApiClient provideEdumailApiClient(OkHttpClient okHttpClient, @EdumailApi String baseUrl) {
        return ApiService.createService(EdumailApiClient.class, okHttpClient, baseUrl);
    }

    @Singleton
    @Provides
    QbacaApiClient provideQbacaApiClient(OkHttpClient okHttpClient, @QbacaApi String baseUrl) {
        return ApiService.createService(QbacaApiClient.class, okHttpClient, baseUrl);
    }

    @Singleton
    @Provides
    RuangguruApiClient provideRuangguruApiClient(OkHttpClient okHttpClient, @RuangguruApi String baseUrl) {
        return ApiService.createService(RuangguruApiClient.class, okHttpClient, baseUrl);
    }

    @Singleton
    @Provides
    IApiManager provideApiManager(ApiManager apiManager) {
        return apiManager;
    }

}
