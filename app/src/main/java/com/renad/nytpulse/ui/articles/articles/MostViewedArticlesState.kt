package com.renad.nytpulse.ui.articles.articles

import com.renad.nytpulse.core.utils.SingleEvent
import com.renad.nytpulse.domain.enities.Article

data class MostViewedArticlesState(
    val isArticleLoaded: Boolean = false,
    val articles: List<Article> = emptyList(),
    val errorMsg: SingleEvent<String>? = null,
    val isLoading: Boolean = true,
)
