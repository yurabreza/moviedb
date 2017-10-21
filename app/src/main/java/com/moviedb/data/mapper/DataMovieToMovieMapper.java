package com.moviedb.data.mapper;

import com.moviedb.data.model.DataMovie;
import com.moviedb.domain.model.Movie;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class DataMovieToMovieMapper implements Function<DataMovie,Movie> {
    @Override
    public Movie apply(@NonNull DataMovie dataMovie) throws Exception {
        return new Movie(dataMovie.getTitle(), dataMovie.getOverview(), dataMovie.getPosterPath(),
                dataMovie.getPopularity().toString(),dataMovie.getId());
    }
}
