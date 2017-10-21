package com.moviedb.presentation.movies_list.di;

import com.moviedb.domain.interactor.GetMoviesList;
import com.moviedb.presentation.PerActivity;
import com.moviedb.presentation.movies_list.MoviesListPresenter;

import dagger.Module;
import dagger.Provides;
@Module
public class MoviesListModule {

    @Provides
    @PerActivity
    MoviesListPresenter provideMoviesListPresenter(GetMoviesList getMoviesList) {
        return new MoviesListPresenter(getMoviesList);
    }
}