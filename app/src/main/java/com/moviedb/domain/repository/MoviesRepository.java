package com.moviedb.domain.repository;

import com.moviedb.domain.model.Movie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface MoviesRepository {
    Observable<List<Movie>> movies();

    Observable<Movie> movieById(Integer id);

    Observable<Boolean> storeMovieToFavorites(Movie movie);

    Observable<Boolean> deleteMovieFromFavorites(Movie movie);

    Flowable<List<Movie>> favoriteMovies();
}
