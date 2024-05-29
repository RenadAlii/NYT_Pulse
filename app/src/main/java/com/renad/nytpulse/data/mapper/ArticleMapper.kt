package com.renad.nytpulse.data.mapper

import com.renad.nytpulse.data.model.MostViewedArticlesModel
import com.renad.nytpulse.domain.enities.Article
import javax.inject.Inject

class ArticlesMapper
@Inject
constructor() {
    fun map(mostViewedArticlesModel: MostViewedArticlesModel): List<Article> =
        mostViewedArticlesModel.articles?.map { article ->
            with(article) {
                val imageUrl = media?.lastOrNull()?.mediaMetadata?.firstOrNull()?.url
                Article(
                    title = title ?: "",
                    abstract = abstract ?: "",
                    byline = byline ?: "",
                    imageUrl = imageUrl ?: "",
                    id = id,
                )
            }
        } ?: emptyList()
}
