package com.paul.song.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.paul.song.network.base.BaseNetworkApi
import com.paul.song.network.utils.TencentUtil
import okhttp3.Interceptor

object TencentNetworkWithEnvelopeApi :
    BaseNetworkApi("https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/") {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getInterceptor(): Interceptor? {
        return Interceptor { chain ->
            val timeStr = TencentUtil.getTimeStr()
            val builder = chain.request().newBuilder()
            builder.addHeader("Source", "source")
            builder.addHeader("Authorization", TencentUtil.getAuthorization(timeStr))
            builder.addHeader("Date", timeStr)
            chain.proceed(builder.build())
        }
    }
}