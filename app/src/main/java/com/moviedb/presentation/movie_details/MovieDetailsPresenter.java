package com.moviedb.presentation.movie_details;

import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.interactor.DefaultObserver;
import com.moviedb.domain.interactor.GetMovieDetails;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.base.BasePresenter;

import javax.inject.Inject;

public class MovieDetailsPresenter extends BasePresenter<MovieDetailsView> {

    private final GetMovieDetails mGetMovieDetails;

    @Inject
    public MovieDetailsPresenter(GetMovieDetails getMovieDetails) {
        mGetMovieDetails = getMovieDetails;
    }

    public void initialize(Integer movieId) {
        mView.showProgress();
        mGetMovieDetails.execute(new MovieDetailsObserver(), GetMovieDetails.Params.forMovie(movieId));
    }

    class MovieDetailsObserver extends DefaultObserver<Movie> {
        @Override
        public void onNext(Movie movie) {
            mView.renderMovie(movie);
        }

        @Override
        public void onComplete() {
            mView.hideProgress();
        }

        @Override
        public void onError(Throwable exception) {
            mView.hideProgress();
            mView.showErrorMessage(new DefaultErrorBundle((Exception) exception));
        }
    }
}
