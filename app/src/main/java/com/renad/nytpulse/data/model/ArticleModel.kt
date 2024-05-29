package com.renad.nytpulse.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleModel(
    @Json(name = "id") val id: Long,
    @Json(name = "published_date") val publishedDate: String?,
    @Json(name = "updated") val updated: String?,
    @Json(name = "section") val section: String?,
    @Json(name = "subsection") val subsection: String?,
    @Json(name = "nytdsection") val nytdsection: String?,
    @Json(name = "adx_keywords") val adxKeywords: String?,
    @Json(name = "byline") val byline: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "abstract") val abstract: String?,
    @Json(name = "media") val media: List<MediaModel>?,
)
