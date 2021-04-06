package com.example.myapplication.Modules.API;

import com.example.myapplication.Models.Authorization.Data;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Setting.ChangePasswordDTO;
import com.example.myapplication.Models.Setting.UpdateUserDTO;
import com.example.myapplication.Models.Setting.UserDTO;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiUser {
    @GET("user/me")
    Observable<Response<BodyResponseDTO<UserDTO>>> getCurrentUser();

    @Multipart
    @POST("user")
    Call<BodyResponseDTO<UpdateUserDTO>> updateUser(
            @Part("firstName") RequestBody fn,
            @Part("lastName") RequestBody ln,
            @Part("gender") RequestBody gen,
            @Part("phone") RequestBody p,
            @Part("nation") RequestBody national,
            @Part("dateOfBirth") RequestBody dob,
            @Part("selfIntroduce") RequestBody des,
            @Part("language") RequestBody lang,
            @Part MultipartBody.Part image);

    @POST("user/change-password")
    Observable<Response<BodyResponseDTO<Data>>> changePass(@Body ChangePasswordDTO changePasswordDTO);

    @POST("user/logout")
    Observable<BodyResponseDTO<Data>> logOut();
}
