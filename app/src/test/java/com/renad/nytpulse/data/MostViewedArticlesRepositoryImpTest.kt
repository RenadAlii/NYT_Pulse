package com.renad.nytpulse.data

import com.appmattus.kotlinfixture.kotlinFixture
import com.renad.nytpulse.data.mapper.ArticlesMapper
import com.renad.nytpulse.data.model.ArticleModel
import com.renad.nytpulse.data.model.MostViewedArticlesModel
import com.renad.nytpulse.data.source.MostViewedArticlesRemoteDataSource
import com.renad.nytpulse.domain.enities.Article
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class MostViewedArticlesRepositoryImpTest {
    val fixture = kotlinFixture()

    private lateinit var repository: MostViewedArticlesRepositoryImp
    private val dataSource: MostViewedArticlesRemoteDataSource = mockk()
    private val mapper: ArticlesMapper = mockk()

    @Before
    fun setUp() {
        repository = MostViewedArticlesRepositoryImp(dataSource, mapper)
    }

    @Test
    fun `getMostViewedArticle returns expected data`() =
        runTest {
            val articlesModel = fixture<List<ArticleModel>>()
            val mostViewedArticlesModel = fixture<MostViewedArticlesModel>().copy(articlesModel)
            val articles = fixture<List<Article>>()
            coEvery { dataSource.getMostViewedArticle(any()) } returns flow {
                emit(
                    mostViewedArticlesModel
                )
            }
            coEvery { mapper.map(mostViewedArticlesModel) } returns articles
            val result = repository.getMostViewedArticles("").first()
            assertEquals(articles, result)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getMostViewedArticle handles exceptions`() =
        runTest {
            coEvery { dataSource.getMostViewedArticle(any()) } throws IllegalStateException()
            advanceUntilIdle()
            assertThrows(IllegalStateException::class.java) {
                runBlocking {
                    repository.getMostViewedArticles("").first()
                }
            }
        }
}
