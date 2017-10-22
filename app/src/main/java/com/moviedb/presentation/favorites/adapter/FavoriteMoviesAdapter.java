package com.moviedb.presentation.favorites.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviedb.BuildConfig;
import com.moviedb.R;
import com.moviedb.domain.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.MovieViewHolder> {

    private final ArrayList<Movie> mMovies;

    public FavoriteMoviesAdapter(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public FavoriteMoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MovieViewHolder(inflater.inflate(R.layout.item_favorite_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image) ImageView mMoviePosterImageView;
        @BindView(R.id.tv_title) TextView mTitleTextView;
        @BindView(R.id.tv_description) TextView mDescriptionTextView;
        @BindView(R.id.tv_rate) TextView mRateTextView;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Movie movie) {
            Picasso.with(mMoviePosterImageView.getContext()).load(BuildConfig.BASE_IMAGE_URL + movie.getPosterUrl()).into(mMoviePosterImageView);
            mTitleTextView.setText(movie.getTitle());
            mDescriptionTextView.setText(movie.getDescription());
            mRateTextView.setText(mRateTextView.getContext().getString(R.string.label_rating, movie.getRating()));

        }
    }
}

