package com.paul.song.network.api

import com.paul.song.network.apiresponse.NetworkResponse
import retrofit2.http.GET
import java.util.*

interface HttpbinOrgApiInterface {

    @GET("status/404")
    suspend fun status404(): NetworkResponse<Objects>

    @GET("status/501")
    suspend fun status501(): NetworkResponse<Objects>
}