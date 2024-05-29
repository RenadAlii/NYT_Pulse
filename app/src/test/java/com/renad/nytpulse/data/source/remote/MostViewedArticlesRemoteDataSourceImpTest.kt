package com.renad.nytpulse.data.source.remote

import com.appmattus.kotlinfixture.kotlinFixture
import com.renad.nytpulse.data.model.MostViewedArticlesModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MostViewedArticlesRemoteDataSourceImpTest {
    private lateinit var dataSource: MostViewedArticlesRemoteDataSourceImp
    private val apiService: NYTApiService = mockk()
    val fixture = kotlinFixture()

    @Before
    fun setUp() {
        dataSource = MostViewedArticlesRemoteDataSourceImp(apiService)
    }

    @Test
    fun `getMostViewedArticle returns expected data`() =
        runTest {
            val expected = fixture<MostViewedArticlesModel>()
            coEvery { apiService.getMostViewedArticle(any()) } returns expected
            val result = dataSource.getMostViewedArticle("").first()
            assertEquals(expected, result)
        }

    @Test
    fun `getMostViewedArticle handles exceptions`() =
        runTest {
            val exception = Exception("Network error")
            coEvery { apiService.getMostViewedArticle(any()) } throws exception
            try {
                dataSource.getMostViewedArticle("").first()
                assertTrue("This line should not be executed", false)
            } catch (e: Exception) {
                assertEquals(exception.message, e.message)
            }
        }
}
