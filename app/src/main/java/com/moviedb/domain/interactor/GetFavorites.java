package com.moviedb.domain.interactor;

import com.moviedb.domain.executor.PostExecutionThread;
import com.moviedb.domain.executor.ThreadExecutor;
import com.moviedb.domain.model.Movie;
import com.moviedb.domain.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetFavorites extends UseCase<List<Movie>, Void> {

    private final MoviesRepository mMoviesRepository;

    @Inject
    GetFavorites(MoviesRepository moviesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMoviesRepository = moviesRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(Void o) {
        return mMoviesRepository.favoriteMovies().toObservable();
    }


}
