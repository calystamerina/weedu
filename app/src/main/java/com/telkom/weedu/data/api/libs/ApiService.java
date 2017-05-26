package com.telkom.weedu.data.api.libs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by ghiyatshanif on 2/23/17.
 * purpose : generate retrofit service class
 */

public class ApiService {

    private ApiService() {

    }

    public static <S> S createService(Class<S> serviceClass,
                                      OkHttpClient okhttpClient, String baseURl) {


        Timber.d("OKHTTP OBJECT HASH" + okhttpClient.toString());
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURl)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(serviceClass);
    }
}
