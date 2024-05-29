package com.renad.nytpulse.data.source.remote

import com.renad.nytpulse.data.source.MostViewedArticlesRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MostViewedArticlesRemoteDataSourceImp
@Inject
constructor(private val apiService: NYTApiService) : MostViewedArticlesRemoteDataSource {
    override fun getMostViewedArticle(period: String) =
        flow { emit(apiService.getMostViewedArticle(period)) }
}
