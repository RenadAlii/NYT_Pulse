package com.renad.nytpulse.data.mapper

import com.renad.nytpulse.data.model.ArticleModel
import com.renad.nytpulse.data.model.MediaMetadataModel
import com.renad.nytpulse.data.model.MediaModel
import com.renad.nytpulse.data.model.MostViewedArticlesModel
import com.renad.nytpulse.domain.enities.Article
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticlesMapperTest {
    private val articlesMapper = ArticlesMapper()

    @Test
    fun `test map function with valid data`() {
        val mediaMetadata =
            MediaMetadataModel(
                url = "http://example.com/image.jpg",
                format = "Standard Thumbnail",
                height = 75,
                width = 75,
            )

        val media =
            MediaModel(
                type = "image",
                mediaMetadata = listOf(mediaMetadata),
            )

        val articleModel =
            ArticleModel(
                id = 123456,
                publishedDate = "2024-05-04",
                updated = "2024-05-06 11:36:29",
                section = "U.S.",
                subsection = "Politics",
                nytdsection = "u.s.",
                adxKeywords = "keyword1, keyword2",
                byline = "By Author Name",
                type = "Article",
                title = "Example Article",
                abstract = "This is an example article.",
                media = listOf(media),
            )

        val mostViewedArticlesModel =
            MostViewedArticlesModel(
                articles = listOf(articleModel),
            )

        val result = articlesMapper.map(mostViewedArticlesModel)

        val expectedArticle =
            Article(
                title = "Example Article",
                abstract = "This is an example article.",
                byline = "By Author Name",
                imageUrl = "http://example.com/image.jpg",
                id = 123456,
            )

        assertEquals(listOf(expectedArticle), result)
    }

    @Test
    fun `test map function with null articles`() {
        val mostViewedArticlesModel =
            MostViewedArticlesModel(
                articles = null,
            )
        val result = articlesMapper.map(mostViewedArticlesModel)
        assertEquals(emptyList<Article>(), result)
    }

    @Test
    fun `test map function with empty articles list`() {
        val mostViewedArticlesModel =
            MostViewedArticlesModel(
                articles = emptyList(),
            )
        val result = articlesMapper.map(mostViewedArticlesModel)
        assertEquals(emptyList<Article>(), result)
    }
}
