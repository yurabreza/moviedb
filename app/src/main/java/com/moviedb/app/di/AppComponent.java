package com.moviedb.app.di;

import com.moviedb.data.di.ApiModule;
import com.moviedb.data.di.DataModule;
import com.moviedb.presentation.movies_list.di.MoviesListComponent;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, DataModule.class, ApiModule.class})
@Singleton
public interface AppComponent {
    MoviesListComponent moviesListComponent();
}

