package com.moviedb.presentation.movies_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.moviedb.R;
import com.moviedb.app.MainApp;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.movies_list.adapter.MoviesListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends AppCompatActivity implements MoviesListView {
    public static final String TAG = MoviesListActivity.class.getSimpleName();

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
        MainApp.getDependencyGraph().initMoviesListComponent();
        initRecyclerView();
        Log.e(TAG, "onCreate: " + mPresenter);
        mPresenter.onAttach(this);
        mPresenter.getStockUpdates();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MoviesListAdapter(mMovies);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void moviesListLoadSuccess(List<Movie> movies) {
        int firstIndex = mMovies.size();
        mMovies.addAll(movies);
        mAdapter.notifyItemRangeChanged(firstIndex, movies.size());
    }

    @Override
    public void moviesListViewError(Throwable throwable) {
        Toast.makeText(this, R.string.error_loading_movies, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        MainApp.getDependencyGraph().releaseStockUpdateComponent();
        mPresenter.onDetach();
        super.onDestroy();
    }
}