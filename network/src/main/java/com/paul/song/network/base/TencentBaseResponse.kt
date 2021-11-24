package com.paul.song.network.base

import com.squareup.moshi.Json

open class TencentBaseResponse {
    @Json(name = "showapi_res_code")
    public val showapiResCode: Int? = null

    @Json(name = "showapi_res_error")
    public val showapiResError: String? = null
}