package com.moviedb.data.api;

import com.moviedb.data.model.MoviesListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesDbService {
    @GET("movie/upcoming")
    Single<MoviesListResponse> movies(@Query("page") String page);
}