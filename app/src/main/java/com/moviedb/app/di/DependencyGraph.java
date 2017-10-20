package com.moviedb.app.di;

import com.moviedb.app.MainApp;
import com.moviedb.presentation.movies_list.di.MoviesListComponent;

public class DependencyGraph {

    private AppComponent mAppComponent;
    private MoviesListComponent mMoviesListComponent;

    public DependencyGraph(MainApp mainApp) {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mainApp))
                .build();
    }

    public MoviesListComponent initMoviesListComponent() {
        mMoviesListComponent = mAppComponent.moviesListComponent();
        return mMoviesListComponent;
    }

    public void releaseStockUpdateComponent() {
        mMoviesListComponent = null;
    }
}
