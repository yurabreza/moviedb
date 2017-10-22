package com.moviedb.data.mapper;

import com.moviedb.data.storage.MovieRealm;
import com.moviedb.domain.model.Movie;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MovieRealmToMovieMapper implements Function<MovieRealm, Movie> {
    @Override
    public Movie apply(@NonNull MovieRealm movieRealm) throws Exception {
        return new Movie(movieRealm.getTitle(), movieRealm.getDescription(), movieRealm.getPosterUrl(),
                movieRealm.getRating(), movieRealm.getMovieId());
    }
}
