package com.gabrielfeo.backintheday.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gabrielfeo.backintheday.model.Favorite;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;
import com.gabrielfeo.backintheday.model.Review;
import com.gabrielfeo.backintheday.model.Trailer;

@Database(entities = {Movie.class,
                      MovieDetails.class,
                      Trailer.class,
                      Review.class,
                      Favorite.class},

          version = 4, exportSchema = false)
public abstract class MovieCache extends RoomDatabase {

    private static final String DATABASE_NAME = "movie_cache";

    public abstract MovieCacheDao dao();

    private static MovieCache instance = null;

    public static synchronized MovieCache getInstance(Context applicationContext) {
        if (instance == null) buildDatabase(applicationContext);
        return instance;
    }

    private static void buildDatabase(Context context) {
        instance = Room.databaseBuilder(context, MovieCache.class, DATABASE_NAME)
                       .allowMainThreadQueries() // TODO Disallow
                       .fallbackToDestructiveMigration()
                       .build();
    }

}
