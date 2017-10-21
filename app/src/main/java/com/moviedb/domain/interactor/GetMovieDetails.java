package com.moviedb.domain.interactor;

import com.moviedb.domain.executor.PostExecutionThread;
import com.moviedb.domain.executor.ThreadExecutor;
import com.moviedb.domain.model.Movie;
import com.moviedb.domain.repository.MoviesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieDetails extends UseCase<Movie, GetMovieDetails.Params> {

    private final MoviesRepository mMoviesRepository;

    @Inject
    GetMovieDetails(MoviesRepository moviesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMoviesRepository = moviesRepository;
    }

    @Override
    Observable<Movie> buildUseCaseObservable(Params params) {
        return mMoviesRepository.movieById(params.movieId);
    }

    public static final class Params {

        private final int movieId;

        private Params(int movieId) {
            this.movieId = movieId;
        }

        public static Params forMovie(int movieId) {
            return new Params(movieId);
        }
    }
}
