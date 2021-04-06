package com.example.myapplication.Modules.API;

import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //rewrite the request to add query lang
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("lang", SharedPreferenceHelper.getString(Utilities.SHARE_LANG))
                .build();
        //rewrite the request to add bearer token
        original  = chain.request().newBuilder()
                .header("Authorization", "Bearer " + SharedPreferenceHelper.getString(Utilities.SHARE_TOKEN))
                .url(url)
                .build();
        return chain.proceed(original);
    }
}