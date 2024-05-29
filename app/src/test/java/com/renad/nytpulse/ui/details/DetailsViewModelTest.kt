package com.renad.nytpulse.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.renad.nytpulse.domain.enities.Article
import com.renad.nytpulse.ui.articles.details.DetailsState
import com.renad.nytpulse.ui.articles.details.DetailsViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@ExperimentalCoroutinesApi
class DetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: DetailsViewModel? = null

    @Before
    fun setup() {
        viewModel = DetailsViewModel()
    }

    @After
    fun tearDown() {
        viewModel = null
    }

    @Test
    fun `test default state`() =
        runTest {
            val state = viewModel?.uiState?.first()
            assertEquals(DetailsState(isLoading = true), state)
        }

    @Test
    fun `test setDetails updates uiState correctly`() =
        runTest {
            val article =
                Article(
                    title = "Sample Title",
                    abstract = "Sample Abstract",
                    byline = "Sample Byline",
                    imageUrl = "https://example.com/image.jpg",
                    id = 1,
                )
            viewModel?.setDetails(article)
            val state = viewModel?.uiState?.first()
            assertEquals(
                DetailsState(
                    title = "Sample Title",
                    abstract = "Sample Abstract",
                    byline = "Sample Byline",
                    imageUrl = "https://example.com/image.jpg",
                    isLoading = false,
                ),
                state,
            )
        }
}
