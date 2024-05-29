package com.renad.nytpulse.domain.enities

data class Article(
    val id: Long,
    val title: String,
    val abstract: String,
    val byline: String,
    val imageUrl: String,
)
