package com.moviedb.presentation.presenter;

import com.moviedb.domain.interactor.DeleteMovieFromFavorites;
import com.moviedb.domain.interactor.GetFavorites;
import com.moviedb.domain.interactor.GetMoviesList;
import com.moviedb.domain.interactor.StoreMovieToFavorites;
import com.moviedb.presentation.movies_list.MoviesListPresenter;
import com.moviedb.presentation.movies_list.MoviesListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MoviesListPresenterTest {

    private MoviesListPresenter mPresenter;

    @Mock private MoviesListView mView;
    @Mock private GetMoviesList mGetMoviesList;
    @Mock private StoreMovieToFavorites mStoreMovieToFavorites;
    @Mock private DeleteMovieFromFavorites mDeleteMovieFromFavorites;
    @Mock private GetFavorites mGetFavorites;

    @Before
    public void setUp() {
        mPresenter = new MoviesListPresenter(mGetMoviesList, mStoreMovieToFavorites,
                mDeleteMovieFromFavorites, mGetFavorites);
        mPresenter.onAttach(mView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testMoviesListPresenterInitialize() {
        mPresenter.initialize();

        verify(mView).showProgress();
        verify(mGetMoviesList).execute(any(DisposableObserver.class), any(Void.class));
        verify(mStoreMovieToFavorites).execute(any(DisposableObserver.class), any(StoreMovieToFavorites.Params.class));
        verify(mDeleteMovieFromFavorites).execute(any(DisposableObserver.class), any(DeleteMovieFromFavorites.Params.class));
        verify(mGetFavorites).execute(any(DisposableObserver.class), any(Void.class));
    }
}
