package com.moviedb.presentation.movies_list.adapter;

import com.moviedb.domain.model.Movie;

public interface MoviesListInteractionListener {
    void setFavorite(Movie movie);

    void unSetFavorite(Movie movie);

    void selectMovie(Movie movie);
}
