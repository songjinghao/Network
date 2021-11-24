package com.paul.song.network

import com.paul.song.network.apiresponse.MoshiResultTypeAdapterFactory
import com.paul.song.network.apiresponse.NetworkResponse
import com.paul.song.network.apiresponse.NetworkResponseAdapterFactory
import com.paul.song.network.base.INetworkRequiredInfo
import com.paul.song.network.commoninterceptor.CommonRequestInterceptor
import com.paul.song.network.commoninterceptor.CommonResponseInterceptor
import com.paul.song.network.error.GlobalErrorHandler
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkApi {
    companion object {
        val BASE_URL: String = "http://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/";
        private var iNetworkRequiredInfo: INetworkRequiredInfo? = null
        private val globalErrorHandler = GlobalErrorHandler()

        fun init(networkRequiredInfo: INetworkRequiredInfo) {
            iNetworkRequiredInfo = networkRequiredInfo
        }

        private val moshiWithBaseResponse = Moshi.Builder()
            .add(MoshiResultTypeAdapterFactory(object : MoshiResultTypeAdapterFactory.Envelope {
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

                override fun isNeedOpenEnvelope(): Boolean {
                    return false
                }

            }))
            .addLast(KotlinJsonAdapterFactory())
            .build()

        private val moshi = Moshi.Builder()
            .add(MoshiResultTypeAdapterFactory(object : MoshiResultTypeAdapterFactory.Envelope {
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

                override fun isNeedOpenEnvelope(): Boolean {
                    return true
                }

            }))
            .addLast(KotlinJsonAdapterFactory())
            .build()

        suspend fun getTencentChannels(): NetworkResponse<NewsChannelsBean> {
            val retrofit: Retrofit
            val retrofitBuild = Retrofit.Builder()
            retrofitBuild.baseUrl(BASE_URL)
            retrofitBuild.client(getOkHttpClient())
            retrofitBuild.addConverterFactory(MoshiConverterFactory.create(moshiWithBaseResponse))
            retrofitBuild.addCallAdapterFactory(NetworkResponseAdapterFactory(globalErrorHandler))
            retrofit = retrofitBuild.build()
            return retrofit.create(TecentNewsApiInterface::class.java).getNewsChannels()
        }

        suspend fun getTencentChannelsWithoutEnvelope(): NetworkResponse<NewsChannelsWithoutEnvelopeBean> {
            val retrofit: Retrofit
            val retrofitBuild = Retrofit.Builder()
            retrofitBuild.baseUrl(BASE_URL)
            retrofitBuild.client(getOkHttpClient())
            retrofitBuild.addConverterFactory(MoshiConverterFactory.create(moshi))
            retrofitBuild.addCallAdapterFactory(NetworkResponseAdapterFactory(globalErrorHandler))
            retrofit = retrofitBuild.build()
            return retrofit.create(TecentNewsApiInterface::class.java).getNewsChannelsWithoutEnvelope()
        }

        private fun getOkHttpClient(): OkHttpClient? {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.addInterceptor(CommonRequestInterceptor(iNetworkRequiredInfo))
            okHttpClientBuilder.addInterceptor(CommonResponseInterceptor())

            if (iNetworkRequiredInfo != null && iNetworkRequiredInfo!!.isDebug()) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            } else {

            }

            return okHttpClientBuilder.build()
        }
    }
}