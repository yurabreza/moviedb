package com.moviedb.presentation.app.di;

import com.moviedb.data.di.ApiModule;
import com.moviedb.data.di.DataModule;
import com.moviedb.domain.executor.PostExecutionThread;
import com.moviedb.domain.executor.ThreadExecutor;
import com.moviedb.presentation.favorites.di.FavoriteMoviesComponent;
import com.moviedb.presentation.movie_details.di.MovieDetailsComponent;
import com.moviedb.presentation.movies_list.di.MoviesListComponent;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, DataModule.class, ApiModule.class})
@Singleton
public interface AppComponent {
    MovieDetailsComponent movieDetailsComponent();
    MoviesListComponent moviesListComponent();
    FavoriteMoviesComponent favoriteMoviesComponent();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
}

