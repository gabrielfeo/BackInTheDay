package com.gabrielfeo.backintheday.data;

import com.gabrielfeo.backintheday.data.callback.SuccessCallback;
import com.gabrielfeo.backintheday.data.moviedb.ApiResponseHandler;
import com.gabrielfeo.backintheday.data.moviedb.MovieDb;
import com.gabrielfeo.backintheday.model.Movie;
import com.gabrielfeo.backintheday.model.MovieDetails;
import com.gabrielfeo.backintheday.model.MoviesResponse;

import java.util.List;

import retrofit2.Callback;

public class AppMovieRepository implements MovieRepository {

    private static final class InstanceHolder {
        private static AppMovieRepository INSTANCE = new AppMovieRepository();
    }

    public static AppMovieRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void getMoviesOfYear(int year, DataRequest<List<Movie>> request) {
        final SuccessCallback<List<Movie>> movieCallback = request.getSuccessCallback();
        final SuccessCallback<MoviesResponse> responseSuccessCallback =
                (response) -> movieCallback.onSuccess(response.getMoviesList());
        MovieDb.getMovieService()
               .getMoviesOfYear(year)
               .enqueue(wrapAsCallback(responseSuccessCallback, request));
    }

    @Override
    public void getMovieDetails(int movieId, DataRequest<MovieDetails> request) {
        MovieDb.getMovieService()
               .getMovieDetails(movieId)
               .enqueue(wrapAsCallback(request));
    }

    private <TResult> Callback<TResult> wrapAsCallback(DataRequest<TResult> originalRequest) {
        return wrapAsCallback(originalRequest.getSuccessCallback(), originalRequest);
    }

    private <TResponse, TResult> Callback<TResponse> wrapAsCallback(SuccessCallback<TResponse> newSuccessCallback,
                                                                    DataRequest<TResult> originalRequest) {
        return new ApiResponseHandler<TResponse>(newSuccessCallback,
                                                 originalRequest.getErrorCallback(),
                                                 originalRequest.getErrorMessage());
    }


}
