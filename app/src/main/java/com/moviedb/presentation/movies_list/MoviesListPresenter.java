package com.moviedb.presentation.movies_list;

import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.interactor.DefaultObserver;
import com.moviedb.domain.interactor.GetMoviesList;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class MoviesListPresenter extends BasePresenter<MoviesListView> {

    private final CompositeDisposable mCompositeDisposable;
    private final GetMoviesList mGetMoviesList;

    @Inject
    public MoviesListPresenter(GetMoviesList getMoviesList) {
        mGetMoviesList = getMoviesList;
        mCompositeDisposable = new CompositeDisposable();
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

    @Override
    public void onDetach() {
        super.onDetach();
        mCompositeDisposable.dispose();
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
        }
    }
}
