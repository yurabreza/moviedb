package com.moviedb.presentation.movie_details;

import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.base.BaseView;
import com.moviedb.presentation.base.HasProgress;


public interface MovieDetailsView extends BaseView, HasProgress {
    void renderMovie(Movie movie);

    void showErrorMessage(DefaultErrorBundle defaultErrorBundle);
}
