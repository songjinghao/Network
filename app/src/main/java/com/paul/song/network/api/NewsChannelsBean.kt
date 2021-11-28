package com.paul.song.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsChannelsBean(
    @Json(name = "showapi_res_body")
    val showapiResBody: ShowapiResBody,
    @Json(name = "showapi_res_code")
    val showapiResCode: Int,
    @Json(name = "showapi_res_error")
    val showapiResError: String
)

@JsonClass(generateAdapter = true)
data class ShowapiResBody(
    @Json(name = "channelList1")
    val channelList: List<Channel>,
    @Json(name = "ret_code")
    val retCode: Int,
    @Json(name = "totalNum")
    val totalNum: Int
)

@JsonClass(generateAdapter = true)
data class Channel(
    @Json(name = "channelId")
    val channelId: String,
    @Json(name = "name")
    val name: String
)