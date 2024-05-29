package com.renad.nytpulse.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object MostViewedArticles : Screen

    @Serializable
    data class Details(
        val id: Long,
        val title: String,
        val abstract: String,
        val byline: String,
        val imageUrl: String,
    ) : Screen
}
