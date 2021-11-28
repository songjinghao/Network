package com.paul.song.network

import com.paul.song.network.base.BaseNetworkApi
import okhttp3.Interceptor

object HttpbinOrgNetworkApi : BaseNetworkApi("https://httpbin.org/") {

    override fun getInterceptor(): Interceptor? {
        return null
    }
}