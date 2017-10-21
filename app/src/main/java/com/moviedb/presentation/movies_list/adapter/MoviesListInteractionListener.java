package com.moviedb.presentation.movies_list.adapter;

public interface MoviesListInteractionListener {
    void setFavorite(Integer id);

    void unSetFavorite(Integer id);

    void selectMovie(Integer id);
}
