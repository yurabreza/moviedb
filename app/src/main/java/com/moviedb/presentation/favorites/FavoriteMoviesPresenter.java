package com.moviedb.presentation.favorites;

import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.interactor.GetFavorites;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class FavoriteMoviesPresenter extends BasePresenter<FavoriteMoviesView> {

    private final GetFavorites mGetFavorites;

    @Inject
    public FavoriteMoviesPresenter(GetFavorites getFavorites) {
        mGetFavorites = getFavorites;
    }

    public void initialize() {
        mGetFavorites.execute(favoritesObserver, null);
    }

    private DisposableObserver favoritesObserver = new DisposableObserver<List<Movie>>() {
        @Override
        public void onNext(@NonNull List<Movie> movies) {
            mView.updateFavorites(movies);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            mView.showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onComplete() {

        }
    };
}
