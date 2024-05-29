package com.renad.nytpulse.domain.usecase

import com.renad.nytpulse.domain.repos.MostViewedArticlesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMostViewedArticlesUseCase
@Inject
constructor(private val repository: MostViewedArticlesRepository) {
    operator fun invoke(period: String) = repository.getMostViewedArticles(period).map {
        it.filter { item -> item.imageUrl.isNotEmpty() }
    }
}
