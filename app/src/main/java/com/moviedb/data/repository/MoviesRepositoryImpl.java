package com.moviedb.data.repository;

import android.util.Log;

import com.moviedb.data.api.MoviesDbService;
import com.moviedb.data.mapper.DataMovieToMovieMapper;
import com.moviedb.data.mapper.MovieDetalsToMovieMapper;
import com.moviedb.data.model.MoviesListResponse;
import com.moviedb.data.storage.MovieRealm;
import com.moviedb.domain.model.Movie;
import com.moviedb.domain.repository.MoviesRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposables;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class MoviesRepositoryImpl implements MoviesRepository {

    public static final String MOVIE_ID = "movieId";
    public static final String TAG = "MoviesLisrPresss";
    private final MoviesDbService mMoviesDbService;
    private final Realm mRealm;

    public MoviesRepositoryImpl(MoviesDbService moviesService) {
        mMoviesDbService = moviesService;

        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<List<Movie>> movies() {
        return mMoviesDbService.movies("1")
                .toObservable()
                .map(MoviesListResponse::getResults)
                .flatMap(Observable::fromIterable)
                .map(new DataMovieToMovieMapper())
                .toList()
                .toObservable();
    }

    @Override
    public Observable<Movie> movieById(Integer id) {
        return mMoviesDbService.movieById(id)
                .toObservable()
                .map(new MovieDetalsToMovieMapper());
    }

    @Override
    public Observable<Boolean> storeMovieToFavorites(Movie movie) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(new MovieRealm(movie.getTitle(), movie.getDescription(), movie.getPosterUrl(),
                movie.getRating(), movie.getMovieId())));
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> deleteMovieFromFavorites(Movie movie) {
        mRealm.executeTransaction(realm -> mRealm.where(MovieRealm.class).equalTo(MOVIE_ID, movie.getMovieId()).findAll().deleteAllFromRealm());
        return Observable.just(true);
    }

    @Override
    public Flowable<List<Movie>> favoriteMovies() {
        return getAll().map(movieRealms -> {
            List<Movie> movies = new ArrayList<>();
            for (MovieRealm movieRealm : movieRealms) {
                Log.d(TAG, "favoriteMovies: " + movieRealm.toString());
                movies.add(new Movie(movieRealm.getTitle(), movieRealm.getDescription(), movieRealm.getPosterUrl(),
                        movieRealm.getRating(), movieRealm.getMovieId()));
            }
            return movies;
        });
    }

    private io.reactivex.Flowable<List<MovieRealm>> getAll() {
        return io.reactivex.Flowable.create((FlowableOnSubscribe<List<MovieRealm>>) emitter -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<MovieRealm> results = realm.where(MovieRealm.class).findAllAsync();
            final RealmChangeListener<RealmResults<MovieRealm>> listener = _realm -> {
                if (results.isLoaded()) {
                    emitter.onNext(results);
                }
            };
            emitter.setDisposable(Disposables.fromRunnable(() -> {
                results.removeChangeListener(listener);
                realm.close();
            }));
            results.addChangeListener(listener);
        }, BackpressureStrategy.LATEST)
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread());
    }

}
