package com.renad.nytpulse.data.source

import com.renad.nytpulse.data.model.MostViewedArticlesModel
import kotlinx.coroutines.flow.Flow

interface MostViewedArticlesRemoteDataSource {
    fun getMostViewedArticle(period: String): Flow<MostViewedArticlesModel>
}
