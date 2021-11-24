package com.paul.song.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsChannelsWithoutEnvelopeBean(
    @Json(name = "channelList")
    val channelList: List<Channel>,
    @Json(name = "ret_code")
    val retCode: Int,
    @Json(name = "totalNum")
    val totalNum: Int
)