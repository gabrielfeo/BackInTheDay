package com.gabrielfeo.backintheday.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gabrielfeo.backintheday.data.MovieRepository;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;

import java.util.List;

@Dao
public abstract class MovieCacheDao implements MovieRepository {

    @Override
    @Query("SELECT * FROM movies WHERE release_date LIKE '%' || :year || '%'")
    public abstract LiveData<List<Movie>> getMoviesOfYear(int year);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<Movie> movies);


    @Override
    @Query("SELECT * FROM movie_details WHERE id = :movieId LIMIT 1")
    public abstract LiveData<MovieDetails> getMovieDetails(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(MovieDetails movieDetails);

}
