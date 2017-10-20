package com.moviedb.domain.interactor;


import com.moviedb.domain.executor.PostExecutionThread;
import com.moviedb.domain.executor.ThreadExecutor;
import com.moviedb.domain.model.Movie;
import com.moviedb.domain.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Movie}.
 */
public class GetMoviesList extends UseCase<List<Movie>, Void> {

  private final MoviesRepository moviesRepository;

  @Inject
  GetMoviesList(MoviesRepository moviesRepository, ThreadExecutor threadExecutor,
                PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.moviesRepository = moviesRepository;
  }

  @Override
  Observable<List<Movie>> buildUseCaseObservable(Void unused) {
    return this.moviesRepository.movies();
  }
}
