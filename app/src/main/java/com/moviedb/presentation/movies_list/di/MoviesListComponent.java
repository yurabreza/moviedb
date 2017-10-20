package com.moviedb.presentation.movies_list.di;

import com.moviedb.presentation.movies_list.MoviesListActivity;

import dagger.Subcomponent;

@Subcomponent(modules = MoviesListModule.class)
@PerActivity
public interface MoviesListComponent {
    void inject(MoviesListActivity activity);
}
