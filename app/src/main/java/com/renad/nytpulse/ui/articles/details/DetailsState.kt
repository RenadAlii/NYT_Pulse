package com.renad.nytpulse.ui.articles.details

data class DetailsState(
    val title: String = "",
    val abstract: String = "",
    val byline: String = "",
    val imageUrl: String = "",
    val isLoading: Boolean = true,
)
