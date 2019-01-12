package com.gabrielfeo.backintheday.data;

import com.gabrielfeo.backintheday.data.callback.ErrorCallback;
import com.gabrielfeo.backintheday.data.callback.SuccessCallback;

public interface DataRequest<T> {

    public DataRequest<T> onSuccess(SuccessCallback<T> successCallback);
    public DataRequest<T> onError(ErrorCallback errorCallback);

}
