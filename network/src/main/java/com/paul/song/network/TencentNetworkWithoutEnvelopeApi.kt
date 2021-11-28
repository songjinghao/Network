package com.paul.song.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.paul.song.network.apiresponse.MoshiResultTypeAdapterFactory
import com.paul.song.network.base.BaseNetworkApi
import com.paul.song.network.utils.TencentUtil
import okhttp3.Interceptor

object TencentNetworkWithoutEnvelopeApi :
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

    override fun getEnvelopeHandler(): MoshiResultTypeAdapterFactory.Envelope? {
        return object : MoshiResultTypeAdapterFactory.Envelope {
            override fun getStatusCodeKey(): String {
                return "showapi_res_code"
            }

            override fun getErrorMessageKey(): String {
                return "showapi_res_error"
            }

            override fun getDataKey(): String {
                return "showapi_res_body"
            }

            override fun doesStatusCodeIndicateSuccess(statusCode: Int): Boolean {
                return statusCode == 0
            }
        }
    }
}