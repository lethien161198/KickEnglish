package com.example.myapplication.Modules.API;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Pronunciation.MediaDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiArtistDetail {
    @GET("artists/{id}")
    Observable<Response<BodyResponseDTO<Artist>>> getArtistDetail(@Path("id") int id);

    @GET("artists/{id}/media")
    Observable<Response<BodyResponseDTO<List<MediaDTO>>>> getMedia(@Path("id") int id);
}
