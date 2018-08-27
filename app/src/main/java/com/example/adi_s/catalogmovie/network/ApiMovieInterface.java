package com.example.adi_s.catalogmovie.network;

import com.example.adi_s.catalogmovie.model.DataListMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMovieInterface {
    @GET("movie/{movie_id}")
    Call<DataListMovie> getDataListMovie(@Path("movie_id") String movie_id, @Query("api_key") String api_key);

    @GET("search/movie")
    Call<DataListMovie> getSearchMovies(@Query("api_key") String api_key, @Query("language") String language, @Query("query") String query);
}
