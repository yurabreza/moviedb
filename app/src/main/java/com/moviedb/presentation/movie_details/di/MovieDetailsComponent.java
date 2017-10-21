package com.moviedb.presentation.movie_details.di;

import com.moviedb.presentation.PerActivity;
import com.moviedb.presentation.movie_details.MovieDetailsActivity;

import dagger.Subcomponent;

@Subcomponent(modules = MovieDetailsModule.class)
@PerActivity
public interface MovieDetailsComponent {
    void inject(MovieDetailsActivity movieDetailsActivity);
}
