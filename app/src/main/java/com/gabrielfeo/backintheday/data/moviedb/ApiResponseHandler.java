package com.gabrielfeo.backintheday.data.moviedb;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gabrielfeo.backintheday.data.callback.ErrorCallback;
import com.gabrielfeo.backintheday.data.callback.SuccessCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponseHandler<T> implements Callback<T> {

    private static final String TAG = ApiResponseHandler.class.getSimpleName();
    private final SuccessCallback<T> successCallback;
    private final ErrorCallback errorCallback;
    private final String errorMessage;

    public ApiResponseHandler(SuccessCallback<T> successCallback,
                              ErrorCallback errorCallback, String errorMessage) {
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
        this.errorMessage = errorMessage;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.body() != null && response.isSuccessful()) {
            successCallback.onSuccess(response.body());
        } else {
            errorCallback.onError(errorMessage);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {
        Log.e(TAG, "API call failed with " + call.request().toString(), throwable);
        throwable.printStackTrace();
        errorCallback.onError(errorMessage);
    }

}
