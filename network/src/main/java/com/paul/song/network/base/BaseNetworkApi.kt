package com.paul.song.network.base

import com.paul.song.network.apiresponse.MoshiResultTypeAdapterFactory
import com.paul.song.network.apiresponse.NetworkResponse
import com.paul.song.network.apiresponse.NetworkResponseAdapterFactory
import com.paul.song.network.commoninterceptor.CommonRequestInterceptor
import com.paul.song.network.commoninterceptor.CommonResponseInterceptor
import com.paul.song.network.environment.EnvironmentActivity
import com.paul.song.network.environment.IEnvironment
import com.paul.song.network.error.GlobalErrorHandler
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseNetworkApi : IEnvironment {
    var mRetrofit: Retrofit
    private var mBaseUrl: String = if (mIsFormal) getTest() else getFormal()
    private val globalErrorHandler = GlobalErrorHandler()

    companion object {
        private var iNetworkRequiredInfo: INetworkRequiredInfo? = null
        private var mIsFormal = true

        fun init(networkRequiredInfo: INetworkRequiredInfo) {
            iNetworkRequiredInfo = networkRequiredInfo
            mIsFormal = EnvironmentActivity.isOfficialEnvironment(networkRequiredInfo)
        }
    }

    private val moshi = Moshi.Builder()
        .add(MoshiResultTypeAdapterFactory(getEnvelopeHandler()))
        .addLast(KotlinJsonAdapterFactory())
        .build()

    open fun <T> getService(service: Class<T>?): T {
        return mRetrofit.create(service)
    }

    constructor() {
        val retrofitBuild = Retrofit.Builder()
        retrofitBuild.baseUrl(mBaseUrl)
        retrofitBuild.client(getOkHttpClient())
        retrofitBuild.addConverterFactory(MoshiConverterFactory.create(moshi))
        retrofitBuild.addCallAdapterFactory(NetworkResponseAdapterFactory(globalErrorHandler))
        mRetrofit = retrofitBuild.build()
    }

    private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")

    private fun getOkHttpClient(): OkHttpClient? {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(CommonRequestInterceptor(iNetworkRequiredInfo))
        okHttpClientBuilder.addInterceptor(CommonResponseInterceptor())

        if (getInterceptor() != null) {
            okHttpClientBuilder.addInterceptor(getInterceptor()!!)
        }

        if (iNetworkRequiredInfo != null && iNetworkRequiredInfo!!.isDebug()) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        } else {
            // throw exception
        }

        return okHttpClientBuilder.build()
    }

    protected abstract fun getInterceptor(): Interceptor?

    protected open fun getEnvelopeHandler(): MoshiResultTypeAdapterFactory.Envelope? {
        return null
    }
}