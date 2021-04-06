package com.example.myapplication.Modules.API;

import com.example.myapplication.Common.Utilities;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final TokenInterceptor interceptor = new TokenInterceptor();
    private static final HttpLoggingInterceptor interceptor1 = new HttpLoggingInterceptor();
    private static final OkHttpClient.Builder client1 = new OkHttpClient.Builder()
            .addInterceptor(interceptor1)
            .addInterceptor(interceptor);

    private static Retrofit retrofit = null;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Utilities.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        interceptor1.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit retrofit1 = builder.client(client1.build()).build();

        return retrofit1.create(serviceClass);
    }

    public static Retrofit getClientDictionary() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en_US/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

}
