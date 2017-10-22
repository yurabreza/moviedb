package com.moviedb.presentation.favorites.di;

import com.moviedb.presentation.PerActivity;
import com.moviedb.presentation.favorites.FavoriteMoviesActivity;

import dagger.Subcomponent;

@Subcomponent(modules = FavoriteMoviesModule.class)
@PerActivity
public interface FavoriteMoviesComponent {
    void inject(FavoriteMoviesActivity favoriteMoviesActivity);
}
