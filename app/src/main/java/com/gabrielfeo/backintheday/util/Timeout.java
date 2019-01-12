package com.gabrielfeo.backintheday.util;

import android.os.Handler;

import com.gabrielfeo.backintheday.data.callback.ErrorCallback;

/**
 * Runs an errorCallback when the specified time has elapsed, if it hasn't been canceled.
 */
public final class Timeout {

    private final int time;
    private final Runnable callback;
    private boolean isCanceled;

    /**
     * Sets up a timeout that will be run when {@link #start()} is called.
     *
     * @param time           max time in milliseconds
     * @param expiryCallback callback run when time has finished
     */
    public Timeout(int time, ErrorCallback expiryCallback) {
        this.time = time;
        this.callback = () -> {
            if (!isCanceled) expiryCallback.onError();
        };
    }

    public void start() {
        new Handler().postDelayed(callback, time);
    }

    public void cancel() {
        isCanceled = true;
    }

}
