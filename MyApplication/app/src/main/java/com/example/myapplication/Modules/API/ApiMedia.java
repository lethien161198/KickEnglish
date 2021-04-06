package com.example.myapplication.Modules.API;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Pronunciation.MediaDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMedia {
    @GET("media")
    Observable<Response<BodyResponseDTO<List<MediaDTO>>>> listMedia(@Query("categoryId") int id,
                                                                    @Query("mediaType") String audio,
                                                                    @Query("searchText") String search);
}
