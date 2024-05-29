package com.renad.nytpulse.domain.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.renad.nytpulse.domain.enities.Article
import com.renad.nytpulse.domain.repos.MostViewedArticlesRepository
import io.mockk.MockKAnnotations
import io.mockk.MockKException
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMostViewedArticlesUseCaseTest {
    @MockK
    private lateinit var repository: MostViewedArticlesRepository

    private lateinit var useCase: GetMostViewedArticlesUseCase
    val fixture = kotlinFixture()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetMostViewedArticlesUseCase(repository)
    }

    private val article = fixture<Article>()

    @Test
    fun `invoke calls repository's getMostViewedArticles`() =
        runTest {
            val fakeArticles = listOf(article)
            coEvery { repository.getMostViewedArticles(any()) } returns flowOf(fakeArticles)
            val result = useCase.invoke("").first()
            assertEquals(fakeArticles, result)
        }

    @Test
    fun `invoke calls repository's exceptions`() =
        runTest {
            coEvery { repository.getMostViewedArticles(any()) } throws MockKException("error")
            Assert.assertThrows(MockKException::class.java) {
                runBlocking {
                    useCase.invoke("").first()
                }
            }
        }
}
