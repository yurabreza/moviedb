package com.moviedb.presentation.movie_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moviedb.BuildConfig;
import com.moviedb.R;
import com.moviedb.domain.exception.DefaultErrorBundle;
import com.moviedb.domain.model.Movie;
import com.moviedb.presentation.app.MainApp;
import com.moviedb.presentation.exception.ErrorMessageFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.internal.Preconditions;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView {
    public static final String INTENT_EXTRA_PARAM_MOVIE_ID = "INTENT_EXTRA_PARAM_MOVIE_ID";
    @BindView(R.id.tv_title) TextView mTitleTextView;
    @BindView(R.id.iv_poster) ImageView mPosterImageView;
    @BindView(R.id.tv_rate) TextView mRateTextView;
    @BindView(R.id.tv_description) TextView mDescriptionTextView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    private Integer mMovieId;

    @Inject MovieDetailsPresenter mMovieDetailsPresenter;

    public static Intent getCallingIntent(Context context, Integer movieId) {
        Intent callingIntent = new Intent(context, MovieDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_MOVIE_ID, movieId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        MainApp.getDependencyGraph().initMoviesDetailsComponent().inject(this);
        mMovieDetailsPresenter.onAttach(this);
        mMovieDetailsPresenter.initialize(getMovieId());
    }

    private Integer getMovieId() {
        final Bundle extras = getIntent().getExtras();
        Preconditions.checkNotNull(extras, "Extras cannot be null");
        return extras.getInt(INTENT_EXTRA_PARAM_MOVIE_ID);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mMovieId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_MOVIE_ID, -1);
        }
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
    public void renderMovie(Movie movie) {
        if (movie != null) {
            Picasso.with(this).load(BuildConfig.BASE_IMAGE_URL + movie.getPosterUrl()).into(mPosterImageView);
            mTitleTextView.setText(movie.getTitle());
            mRateTextView.setText(getString(R.string.label_rating, movie.getRating()));
            mDescriptionTextView.setText(movie.getDescription());
        }
    }

    @Override
    public void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
        String errorMessage = ErrorMessageFactory.create(this, defaultErrorBundle.getException());
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mMovieDetailsPresenter.onDetach();
        MainApp.getDependencyGraph().releaseMovieDetailsComponent();
        super.onDestroy();
    }
}
