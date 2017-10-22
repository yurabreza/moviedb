package com.moviedb.presentation.favorites.di;

import com.moviedb.domain.interactor.GetFavorites;
import com.moviedb.presentation.PerActivity;
import com.moviedb.presentation.favorites.FavoriteMoviesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FavoriteMoviesModule {
    @Provides
    @PerActivity
    FavoriteMoviesPresenter provideMoviesListPresenter(GetFavorites getFavorites) {
        return new FavoriteMoviesPresenter(getFavorites);
    }
}
