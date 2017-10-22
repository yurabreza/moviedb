package com.moviedb.data.storage;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class MovieRealm extends RealmObject {
    private String title;
    @PrimaryKey
    private Integer movieId;
    private String description;
    private String posterUrl;
    private String rating;
    private boolean isFavorite;

    public MovieRealm() {
    }

    public MovieRealm(String title, String description, String posterUrl, String rating, Integer movieId) {
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.movieId = movieId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
