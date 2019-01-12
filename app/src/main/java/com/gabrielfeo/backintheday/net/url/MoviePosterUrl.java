package com.gabrielfeo.backintheday.net.url;

import android.net.Uri;

public class MoviePosterUrl {

    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/";
    private static final String POSTER_SIZE = "w342";

    private MoviePosterUrl() {}

    public static Uri getFor(String path) {
        return Uri.parse(POSTER_BASE_URL + POSTER_SIZE + path);
    }

}
