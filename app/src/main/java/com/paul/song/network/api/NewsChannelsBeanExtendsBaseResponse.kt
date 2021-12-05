package com.paul.song.network.api

import com.paul.song.network.bean.TencentBaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsChannelsBeanExtendsBaseResponse(
    @Json(name = "showapi_res_body")
    val showapiResBody: NewsChannelsBean
) : TencentBaseResponse()
