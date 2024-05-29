package com.renad.nytpulse.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaMetadataModel(
    @Json(name = "url") val url: String?,
    @Json(name = "format") val format: String?,
    @Json(name = "height") val height: Int?,
    @Json(name = "width") val width: Int?,
)
