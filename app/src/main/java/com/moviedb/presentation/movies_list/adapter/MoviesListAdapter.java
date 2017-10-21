package com.moviedb.presentation.movies_list.adapter;

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
import butterknife.OnClick;


public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {
    private final ArrayList<Movie> mMovies;
    private MoviesListInteractionListener mListener;

    public MoviesListAdapter(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public MoviesListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    public void setOnItemClickListener(MoviesListInteractionListener moviesListInteractionListener) {
        mListener = moviesListInteractionListener;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image) ImageView mMoviePosterImageView;
        @BindView(R.id.iv_like) ImageView mLikeImageView;
        @BindView(R.id.tv_title) TextView mTitleTextView;
        @BindView(R.id.tv_description) TextView mDescriptionTextView;
        @BindView(R.id.tv_rate) TextView mRateTextView;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        // TODO: 21.10.17 get context right way
        void bind(Movie movie) {
            Picasso.with(mMoviePosterImageView.getContext()).load(BuildConfig.BASE_IMAGE_URL + movie.getPosterUrl()).into(mMoviePosterImageView);
            mTitleTextView.setText(movie.getTitle());
            mDescriptionTextView.setText(movie.getDescription());
            mRateTextView.setText(mRateTextView.getContext().getString(R.string.label_rating, movie.getRating()));
            mLikeImageView.setSelected(movie.isFavorite());
        }

        @OnClick(R.id.iv_like)
        void onImageClick(View view) {
            if (mListener == null) throw new RuntimeException("mListener not set");
            final Movie movie = mMovies.get(getAdapterPosition());
            final Integer movieId = movie.getMovieId();
            if (view.isSelected()) {
                mListener.setFavorite(movieId);
            } else {
                mListener.unSetFavorite(movieId);
            }
            final boolean newSelectedState = !view.isSelected();
            view.setSelected(newSelectedState);
            movie.setFavorite(newSelectedState);
        }

        @OnClick(R.id.cv_root)
        void onCardClick() {
            if (mListener == null) throw new RuntimeException("mListener not set");
            mListener.selectMovie(mMovies.get(getAdapterPosition()).getMovieId());
        }
    }
}
