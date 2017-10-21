package com.moviedb.data.mapper;

import com.moviedb.data.model.movie_details.MovieDetailsResponse;
import com.moviedb.domain.model.Movie;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MovieDetalsToMovieMapper implements Function<MovieDetailsResponse, Movie> {
    @Override
    public Movie apply(@NonNull MovieDetailsResponse movieDetailsResponse) throws Exception {
        return new Movie(movieDetailsResponse.getTitle(), movieDetailsResponse.getOverview(),
                movieDetailsResponse.getPosterPath(), movieDetailsResponse.getPopularity().toString(),
                movieDetailsResponse.getId());
    }
}
