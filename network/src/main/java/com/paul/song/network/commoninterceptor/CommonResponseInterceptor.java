package com.paul.song.network.commoninterceptor;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CommonResponseInterceptor implements Interceptor {
    private static final String TAG = "ResponseInterceptor";
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        Log.d(TAG, "requestTime:" + (System.currentTimeMillis() - requestTime));
        return response;
    }
}
