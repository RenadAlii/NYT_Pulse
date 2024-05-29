package com.renad.nytpulse.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MostViewedArticlesModel(
    @Json(name = "results") val articles: List<ArticleModel>?,
)
