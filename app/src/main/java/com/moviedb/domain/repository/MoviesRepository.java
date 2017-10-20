package com.moviedb.domain.repository;

import com.moviedb.domain.model.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface MoviesRepository {
    Observable<List<Movie>> movies();
}
