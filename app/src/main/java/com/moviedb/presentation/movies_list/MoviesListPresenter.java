package com.moviedb.presentation.movies_list;

import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.interactor.DefaultObserver;
import com.moviedb.domain.interactor.DeleteMovieFromFavorites;
import com.moviedb.domain.interactor.GetFavorites;
import com.moviedb.domain.interactor.GetMoviesList;
import com.moviedb.domain.interactor.StoreMovieToFavorites;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

public class MoviesListPresenter extends BasePresenter<MoviesListView> {

    public static final String TAG = "MoviesLisrPresss";
    private final GetMoviesList mGetMoviesList;
    private final StoreMovieToFavorites mStoreMovieToFavorites;
    private final DeleteMovieFromFavorites mDeleteMovieFromFavorites;
    private final GetFavorites mGetFavorites;

    @Inject
    public MoviesListPresenter(GetMoviesList getMoviesList,
                               StoreMovieToFavorites storeMovieToFavorites,
                               DeleteMovieFromFavorites deleteMovieFromFavorites,
                               GetFavorites getFavorites) {
        mGetMoviesList = getMoviesList;
        mStoreMovieToFavorites = storeMovieToFavorites;
        mDeleteMovieFromFavorites = deleteMovieFromFavorites;
        mGetFavorites = getFavorites;
    }

    public void initialize() {
        loadMoviesList();
    }

    private void loadMoviesList() {
        mView.showProgress();
        mGetMoviesList.execute(new MoviesListObserver(), null);
    }

    void hideViewLoading() {
        mView.hideProgress();
    }

    void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
        mView.displayError(defaultErrorBundle);
    }

    public void unsetMovieFavorite(Movie movie) {
        mDeleteMovieFromFavorites.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean o) {
                super.onNext(o);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
            }
        }, DeleteMovieFromFavorites.Params.forMovie(movie));
    }

    public void setMoviesFavorite(Movie movie) {
        mStoreMovieToFavorites.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean o) {
                super.onNext(o);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onError(Throwable exception) {
                super.onError(exception);
            }
        }, StoreMovieToFavorites.Params.forMovie(movie));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mView = null;
    }

    private final class MoviesListObserver extends DefaultObserver<List<Movie>> {
        @Override
        public void onComplete() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable exception) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) exception));
        }

        @Override
        public void onNext(List<Movie> movies) {
            mView.moviesListLoadSuccess(movies);
            mGetFavorites.execute(new DefaultObserver<List<Movie>>() {
                @Override
                public void onNext(List<Movie> movies) {
                    super.onNext(movies);
                    if (mView != null)
                        mView.updateFavorites(movies);
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                }

                @Override
                public void onError(Throwable exception) {
                    super.onError(exception);
                    exception.printStackTrace();
                }
            }, null);
        }
    }
}
