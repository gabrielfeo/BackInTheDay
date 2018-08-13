package com.gabrielfeo.backintheday.altclass;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSource;

public final class ResponseBodyLoggingInterceptor implements Interceptor {

	private final HttpLoggingInterceptor.Logger logger;

	public ResponseBodyLoggingInterceptor(@NonNull HttpLoggingInterceptor.Logger logger) {
		this.logger = logger;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Response response = chain.proceed(chain.request());
		if (response.body() != null) {
			BufferedSource bodySource = response.body().source();
			bodySource.request(Integer.MAX_VALUE);
			String responseBody = bodySource.buffer().snapshot().utf8();
			logger.log(responseBody);
		}
		return response;
	}
}
