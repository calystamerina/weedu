package com.telkom.weedu.data.api.libs;

import android.support.annotation.Nullable;

import com.telkom.weedu.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose : generate retrofit service class
 */

public class OkHttpClientFactory {

    private OkHttpClientFactory() {

    }

    public static OkHttpClient create(@Nullable HeaderInterceptor headerInterceptor
            , @Nullable ParameterInterceptor parameterInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        if (headerInterceptor != null) {
            builder.addInterceptor(headerInterceptor);
        }

        if (parameterInterceptor != null) {
            builder.addInterceptor(parameterInterceptor);
        }

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor).build();
        }
        Dispatcher dispatcher=new Dispatcher();
        dispatcher.setMaxRequests(20);
        builder.dispatcher(dispatcher);

        return builder.build();
    }
}
