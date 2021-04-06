package com.example.myapplication.Modules.API;

import com.example.myapplication.Models.Authorization.Token;
import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.Authorization.JwtResponse;
import com.example.myapplication.Models.Authorization.SignInRequest;
import com.example.myapplication.Models.Authorization.SignUpRequest;
import com.example.myapplication.Models.BodyResponseDTO;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiAccount {
    @POST("auth/sign-up")
    Call<BodyResponseDTO<Data>> SignUp(
            @Body SignUpRequest param);

    @POST("auth/sign-in")
    Call<BodyResponseDTO<JwtResponse>> SignIn(
            @Body SignInRequest param);
}
