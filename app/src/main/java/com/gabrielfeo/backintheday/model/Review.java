package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(tableName = "movie_reviews")
public class Review {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    @JsonProperty("id")
    private final String id;

    @ColumnInfo(name = "movie_id")
    @JsonIgnore
    private int movieId;

    @ColumnInfo(name = "author")
    @JsonProperty("author")
    private final String author;

    @ColumnInfo(name = "content")
    @JsonProperty("content")
    private final String content;

    @ColumnInfo(name = "url")
    @JsonProperty("url")
    private final String url;

    @Ignore
    @JsonCreator
    public Review(String id, String author, String content, String url) {
        this(id, 0, author, content, url);
    }

    public Review(String id, int movieId, String author, String content, String url) {
        this.id = id;
        this.movieId = movieId;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

}
