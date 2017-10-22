package com.moviedb.presentation.movies_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.moviedb.R;
import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.app.MainApp;
import com.moviedb.presentation.exception.ErrorMessageFactory;
import com.moviedb.presentation.favorites.FavoriteMoviesActivity;
import com.moviedb.presentation.movie_details.MovieDetailsActivity;
import com.moviedb.presentation.movies_list.adapter.MoviesListAdapter;
import com.moviedb.presentation.movies_list.adapter.MoviesListInteractionListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends AppCompatActivity implements MoviesListView, MoviesListInteractionListener {
    public static final String TAG = "MoviesActivityDebug";

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private MoviesListAdapter mAdapter;

    @Inject MoviesListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainApp.getDependencyGraph().initMoviesListComponent().inject(this);
        initRecyclerView();

        mPresenter.onAttach(this);
        mPresenter.initialize();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MoviesListAdapter(mMovies);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            startActivity(FavoriteMoviesActivity.getCallingIntent(this));
            return true;
        }
        return false;
    }

    //region MoviesListView
    @Override
    public void showProgress() {
        Log.d(TAG, "showProgress: ");
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        Log.d(TAG, "hideProgress: ");
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void moviesListLoadSuccess(List<Movie> movies) {
        Log.d(TAG, "moviesListLoadSuccess: " + movies.size());
        int firstIndex = mMovies.size();
        mMovies.addAll(movies);
        mAdapter.notifyItemRangeChanged(firstIndex, movies.size());
    }

    @Override
    public void displayError(DefaultErrorBundle defaultErrorBundle) {
        String errorMessage = ErrorMessageFactory.create(this, defaultErrorBundle.getException());
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateFavorites(List<Movie> movies) {
        for (Movie favMovie : movies) {
            for (Movie movie : mMovies) {
                Log.d(TAG, "updateFavorites: " + movie.getMovieId());
                Log.d(TAG, "updateFavorites: " + favMovie.getMovieId());
                if (movie.getMovieId().equals(favMovie.getMovieId())) {
                    movie.setFavorite(true);
                    Log.d(TAG, "updateFavorites: " + movie.toString());
                    mAdapter.notifyItemChanged(mMovies.indexOf(movie));
                }
            }
        }
    }
    //endregion

    //region MoviesListInteractionListener
    @Override
    public void setFavorite(Movie movie) {
        mPresenter.setMoviesFavorite(movie);
    }

    @Override
    public void unSetFavorite(Movie movie) {
        mPresenter.unsetMovieFavorite(movie);
    }

    @Override
    public void selectMovie(Movie movie) {
        startActivity(MovieDetailsActivity.getCallingIntent(MoviesListActivity.this, movie.getMovieId()));
    }
    //endregion

    @Override
    protected void onDestroy() {
        MainApp.getDependencyGraph().releaseMoviesListComponent();
        mPresenter.onDetach();
        super.onDestroy();
    }
}