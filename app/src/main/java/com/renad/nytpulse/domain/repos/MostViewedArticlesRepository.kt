package com.renad.nytpulse.domain.repos

import com.renad.nytpulse.domain.enities.Article
import kotlinx.coroutines.flow.Flow

interface MostViewedArticlesRepository {
    fun getMostViewedArticles(period: String): Flow<List<Article>>
}
