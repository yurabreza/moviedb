package com.moviedb.presentation.favorites;

import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.base.BaseView;

import java.util.List;


public interface FavoriteMoviesView extends BaseView{

    void updateFavorites(List<Movie> movies);

    void showErrorMessage(DefaultErrorBundle defaultErrorBundle);

}
