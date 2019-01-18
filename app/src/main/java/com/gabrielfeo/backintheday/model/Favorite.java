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
    private final int movieId;

    public Favorite(@NonNull int movieId) {
        this.movieId = movieId;
    }

    @NonNull
    public int getMovieId() {
        return movieId;
    }

}
