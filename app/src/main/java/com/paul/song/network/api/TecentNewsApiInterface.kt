package com.paul.song.network.api

import com.paul.song.network.apiresponse.NetworkResponse
import retrofit2.http.GET

interface TecentNewsApiInterface {
    @GET("release/channel")
    suspend fun getNewsChannelsWithEnvelope(
    ): NetworkResponse<NewsChannelsBeanExtendsBaseResponse>

    @GET("release/channel")
    suspend fun getNewsChannelsWithoutEnvelope(
    ): NetworkResponse<NewsChannelsBean>

    @GET("release/channel")
    suspend fun getNewsChannelsWithNpe(
    ): NetworkResponse<NewsChannelsJsonNpeBean>
}