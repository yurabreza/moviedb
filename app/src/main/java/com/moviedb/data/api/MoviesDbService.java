package com.moviedb.data.api;

import com.moviedb.data.model.MoviesListResponse;
import com.moviedb.data.model.movie_details.MovieDetailsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesDbService {
    @GET("movie/upcoming")
    Single<MoviesListResponse> movies(@Query("page") String page);

    @GET("movie/{id}")
    Single<MovieDetailsResponse> movieById(@Path("id") Integer id);
}