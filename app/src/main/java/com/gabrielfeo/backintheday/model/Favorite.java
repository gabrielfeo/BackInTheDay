package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorites")
public class Favorite {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private final String movieId;

    public Favorite(@NonNull String movieId) {
        this.movieId = movieId;
    }

    @NonNull
    public String getMovieId() {
        return movieId;
    }

}
