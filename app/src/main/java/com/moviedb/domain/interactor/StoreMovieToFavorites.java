package com.moviedb.domain.interactor;

import com.moviedb.domain.executor.PostExecutionThread;
import com.moviedb.domain.executor.ThreadExecutor;
import com.moviedb.domain.model.Movie;
import com.moviedb.domain.repository.MoviesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class StoreMovieToFavorites extends UseCase<Boolean, StoreMovieToFavorites.Params> {

    private final MoviesRepository mMoviesRepository;

    @Inject
    public StoreMovieToFavorites(MoviesRepository moviesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMoviesRepository = moviesRepository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(Params params) {
        return mMoviesRepository.storeMovieToFavorites(params.movie);
    }

    public static final class Params {

        private final Movie movie;

        private Params(Movie movie) {
            this.movie = movie;
        }

        public static StoreMovieToFavorites.Params forMovie(Movie movie) {
            return new StoreMovieToFavorites.Params(movie);
        }
    }
}
