package com.moviedb.data.repository;

import com.moviedb.data.api.MoviesDbService;
import com.moviedb.data.mapper.DataMovieToMovieMapper;
import com.moviedb.data.mapper.MovieDetalsToMovieMapper;
import com.moviedb.data.model.MoviesListResponse;
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
        return mMoviesDbService.movies("1")
                .toObservable()
                .map(MoviesListResponse::getResults)
                .flatMap(Observable::fromIterable)
                .map(new DataMovieToMovieMapper())
                .toList()
                .toObservable();
    }

    @Override
    public Observable<Movie> movieById(Integer id) {
        return mMoviesDbService.movieById(id)
                .toObservable()
                .map(new MovieDetalsToMovieMapper());
    }
}
