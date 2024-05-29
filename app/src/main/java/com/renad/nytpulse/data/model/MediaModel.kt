package com.renad.nytpulse.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaModel(
    @Json(name = "type") val type: String?,
    @Json(name = "media-metadata") val mediaMetadata: List<MediaMetadataModel>?,
)
