package com.moviedb.presentation.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.moviedb.R;
import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.app.MainApp;
import com.moviedb.presentation.exception.ErrorMessageFactory;
import com.moviedb.presentation.favorites.adapter.FavoriteMoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoriteMoviesActivity extends AppCompatActivity implements FavoriteMoviesView {

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, FavoriteMoviesActivity.class);
        return callingIntent;
    }

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private FavoriteMoviesAdapter mAdapter;

    @Inject FavoriteMoviesPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        ButterKnife.bind(this);
        MainApp.getDependencyGraph().iniFavoriteMoviesComponent().inject(this);
        initRecyclerView();

        mPresenter.onAttach(this);
        mPresenter.initialize();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FavoriteMoviesAdapter(mMovies);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void updateFavorites(List<Movie> movies) {
        int firstIndex = mMovies.size();
        mMovies.addAll(movies);
        mAdapter.notifyItemRangeChanged(firstIndex, movies.size());
    }

    @Override
    public void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
        String errorMessage = ErrorMessageFactory.create(this, defaultErrorBundle.getException());
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
        MainApp.getDependencyGraph().releaseFavoriteMoviesComponent();
    }
}
