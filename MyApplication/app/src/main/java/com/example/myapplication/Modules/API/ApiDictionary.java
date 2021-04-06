package com.example.myapplication.Modules.API;

import com.example.myapplication.Models.Dictionary.ResponeDictionary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiDictionary {
    @GET()
    Call<List<ResponeDictionary>> tranlslate(@Url String s);
}
