package com.moviedb.presentation.movies_list.di;

import com.moviedb.domain.repository.MoviesRepository;
import com.moviedb.presentation.movies_list.MoviesListPresenter;

import dagger.Module;
import dagger.Provides;
@Module
public class MoviesListModule {

    @Provides
    @PerActivity
    MoviesListPresenter provideMoviesListPresenter(MoviesRepository moviesRepository) {
        return new MoviesListPresenter(moviesRepository);
    }
}