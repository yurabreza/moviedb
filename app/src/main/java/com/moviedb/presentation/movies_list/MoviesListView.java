package com.moviedb.presentation.movies_list;

import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.base.BaseView;
import com.moviedb.presentation.base.HasProgress;

import java.util.List;


public interface MoviesListView extends BaseView, HasProgress {
    void moviesListLoadSuccess(List<Movie> movies);

    void moviesListViewError(Throwable throwable);
}
