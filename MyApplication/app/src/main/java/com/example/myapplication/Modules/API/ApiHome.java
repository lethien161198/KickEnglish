package com.example.myapplication.Modules.API;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Home.CategoryDTO;
import com.example.myapplication.Models.Home.MemberLessonDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHome {
    @GET("artists")
    Observable<Response<BodyResponseDTO<List<Artist>>>> listArtist();

    @GET("categories")
    Observable<Response<BodyResponseDTO<List<CategoryDTO>>>> listCategory(@Query("categoryType") String type);

    @GET("media/memberLesson")
    Observable<Response<BodyResponseDTO<List<MemberLessonDTO>>>> listMemberLesson();
}
