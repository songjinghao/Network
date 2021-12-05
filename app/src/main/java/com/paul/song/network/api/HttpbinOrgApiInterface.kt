package com.paul.song.network.api

import com.paul.song.network.apiresponse.NetworkResponse
import com.paul.song.network.bean.HttpbinOrgBaseResponse
import retrofit2.http.GET
import java.util.*

interface HttpbinOrgApiInterface {

    @GET("status/404")
    suspend fun status404(): NetworkResponse<HttpbinOrgBaseResponse>

    @GET("status/501")
    suspend fun status501(): NetworkResponse<HttpbinOrgBaseResponse>
}