package com.moviedb.presentation.app.di;

import com.moviedb.presentation.app.MainApp;
import com.moviedb.presentation.favorites.di.FavoriteMoviesComponent;
import com.moviedb.presentation.movie_details.di.MovieDetailsComponent;
import com.moviedb.presentation.movies_list.di.MoviesListComponent;

public class DependencyGraph {

    private AppComponent mAppComponent;
    private MoviesListComponent mMoviesListComponent;
    private MovieDetailsComponent mMovieDetailsComponent;
    private FavoriteMoviesComponent mFavoriteMoviesComponent;

    public DependencyGraph(MainApp mainApp) {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mainApp))
                .build();
    }

    public MoviesListComponent initMoviesListComponent() {
        mMoviesListComponent = mAppComponent.moviesListComponent();
        return mMoviesListComponent;
    }


    public MovieDetailsComponent initMoviesDetailsComponent() {
        mMovieDetailsComponent = mAppComponent.movieDetailsComponent();
        return mMovieDetailsComponent;
    }

    public FavoriteMoviesComponent iniFavoriteMoviesComponent() {
        mFavoriteMoviesComponent = mAppComponent.favoriteMoviesComponent();
        return mFavoriteMoviesComponent;
    }

    public void releaseMoviesListComponent() {
        mMoviesListComponent = null;
    }

    public void releaseMovieDetailsComponent() {
        mMovieDetailsComponent = null;
    }
    public void releaseFavoriteMoviesComponent() {
        mFavoriteMoviesComponent = null;
    }
}
