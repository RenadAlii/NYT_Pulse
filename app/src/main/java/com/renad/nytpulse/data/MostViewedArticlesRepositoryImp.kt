package com.renad.nytpulse.data

import com.renad.nytpulse.data.mapper.ArticlesMapper
import com.renad.nytpulse.data.source.MostViewedArticlesRemoteDataSource
import com.renad.nytpulse.domain.repos.MostViewedArticlesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MostViewedArticlesRepositoryImp
@Inject
constructor(
    private val dataSource: MostViewedArticlesRemoteDataSource,
    private val articlesMapper: ArticlesMapper,
) : MostViewedArticlesRepository {
    override fun getMostViewedArticles(period: String) =
        dataSource.getMostViewedArticle(period).map(articlesMapper::map)
}
