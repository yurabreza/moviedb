package com.moviedb.presentation.movies_list;

import com.moviedb.domain.repository.MoviesRepository;
import com.moviedb.presentation.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MoviesListPresenter extends BasePresenter<MoviesListView> {

    private final CompositeDisposable mCompositeDisposable ;
    private final MoviesRepository mMoviesRepository;

    @Inject
    public MoviesListPresenter(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getStockUpdates() {
        mCompositeDisposable.add(mMoviesRepository.movies()
                .doOnSubscribe(disposable -> mView.showProgress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(mView::hideProgress)
                .subscribe(mView::moviesListLoadSuccess, mView::moviesListViewError));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCompositeDisposable.dispose();
    }
}
