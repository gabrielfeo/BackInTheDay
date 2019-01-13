package com.gabrielfeo.backintheday.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.gabrielfeo.backintheday.data.local.typeconverter.VideoListSerializer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "movie_videos")
@TypeConverters({VideoListSerializer.class})
public class MovieVideosResponse {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    @SerializedName("id")
    private final int movieId;

    @ColumnInfo(name = "videos")
    @SerializedName("results")
    private final List<Video> videos;

    public MovieVideosResponse(int movieId, List<Video> videos) {
        this.movieId = movieId;
        this.videos = videos;
    }

    public int getMovieId() {
        return movieId;
    }

    public List<Video> getVideos() {
        return videos;
    }

}
