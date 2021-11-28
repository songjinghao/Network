package com.paul.song.network.commoninterceptor;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.paul.song.network.base.INetworkRequiredInfo;
import com.paul.song.network.utils.TencentUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonRequestInterceptor implements Interceptor {
    private INetworkRequiredInfo requiredInfo;
    public CommonRequestInterceptor(INetworkRequiredInfo requiredInfo) {
        this.requiredInfo = requiredInfo;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String timeStr = TencentUtil.getTimeStr();
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("os", "android");
        builder.addHeader("appVersion", this.requiredInfo.getAppVersionCode());
        return chain.proceed(builder.build());
    }
}
