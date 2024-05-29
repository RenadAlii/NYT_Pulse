package com.renad.nytpulse.ui.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.renad.nytpulse.core.utils.SingleEvent
import com.renad.nytpulse.domain.enities.Article
import com.renad.nytpulse.domain.usecase.GetMostViewedArticlesUseCase
import com.renad.nytpulse.ui.articles.articles.MostViewedArticlesState
import com.renad.nytpulse.ui.articles.articles.MostViewedArticlesViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@ExperimentalCoroutinesApi
class MostViewedArticlesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase = mockk<GetMostViewedArticlesUseCase>(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    private var viewModel: MostViewedArticlesViewModel? = null

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MostViewedArticlesViewModel(useCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
        viewModel = null
    }

    @Test
    fun `test loadArticles updates uiState correctly`() =
        runTest {
            val articles =
                listOf(
                    Article(
                        title = "Title 1",
                        abstract = "Abstract 1",
                        byline = "Byline 1",
                        imageUrl = "https://example.com/image1.jpg",
                        id = 1,
                    ),
                    Article(
                        title = "Title 2",
                        abstract = "Abstract 2",
                        byline = "Byline 2",
                        imageUrl = "https://example.com/image2.jpg",
                        id = 2,
                    ),
                )

            coEvery { useCase.invoke("7") } returns flowOf(articles)
            viewModel?.loadArticles("7")
            advanceUntilIdle()

            val state = viewModel?.uiState?.value
            assertEquals(
                MostViewedArticlesState(
                    articles = articles,
                    isLoading = false,
                    isArticleLoaded = true
                ), state
            )
        }

    @Test
    fun `test loadArticles handles error correctly`() =
        runTest {
            val errorMessage = ""
            coEvery { useCase.invoke("7") } returns flow { throw Exception(errorMessage) }
            viewModel?.loadArticles("7")
            advanceUntilIdle()

            val state = viewModel?.uiState?.value
            assertEquals(
                MostViewedArticlesState(
                    errorMsg = SingleEvent("An unknown error occurred."),
                    isLoading = false
                ), state
            )
        }
}
