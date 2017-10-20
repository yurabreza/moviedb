package com.moviedb.data.repository;

import com.moviedb.data.api.MoviesDbService;
import com.moviedb.domain.model.Movie;
import com.moviedb.domain.repository.MoviesRepository;

import java.util.List;

import io.reactivex.Observable;


public class MoviesRepositoryImpl implements MoviesRepository {

    private final MoviesDbService mMoviesDbService;

    public MoviesRepositoryImpl(MoviesDbService moviesService) {
        mMoviesDbService = moviesService;
    }

    @Override
    public Observable<List<Movie>> movies() {
        return null;
    }
}
