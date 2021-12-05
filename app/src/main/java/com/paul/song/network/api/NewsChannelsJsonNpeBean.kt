package com.paul.song.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsChannelsJsonNpeBean(
    @Json(name = "channel_list")
    val channelList: List<Channel>,
    @Json(name = "ret_code")
    val retCode: Int,
    @Json(name = "totalNum")
    val totalNum: Int
)