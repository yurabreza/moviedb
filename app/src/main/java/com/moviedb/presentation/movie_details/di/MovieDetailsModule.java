package com.moviedb.presentation.movie_details.di;

import com.moviedb.domain.interactor.GetMovieDetails;
import com.moviedb.presentation.PerActivity;
import com.moviedb.presentation.movie_details.MovieDetailsPresenter;

import dagger.Module;
import dagger.Provides;
@Module
public class MovieDetailsModule {
    @Provides
    @PerActivity
    MovieDetailsPresenter provideMoviesListPresenter(GetMovieDetails getMovieDetails) {
        return new MovieDetailsPresenter(getMovieDetails);
    }
}
