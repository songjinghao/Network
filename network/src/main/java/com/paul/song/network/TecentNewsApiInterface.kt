package com.paul.song.network

import com.paul.song.network.apiresponse.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TecentNewsApiInterface {
    @GET("release/channel")
    suspend fun getNewsChannels(): NetworkResponse<NewsChannelsBean>

    @GET("release/channel")
    suspend fun getNewsChannelsWithoutEnvelope(): NetworkResponse<NewsChannelsWithoutEnvelopeBean>
}