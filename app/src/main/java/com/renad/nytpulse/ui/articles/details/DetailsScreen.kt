package com.renad.nytpulse.ui.articles.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.renad.nytpulse.domain.enities.Article

@Composable
fun ArticleDetailsScreen(
    article: Article,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
    val state by detailsViewModel.uiState.collectAsStateWithLifecycle()


    // Load articles initially
    if (state.isLoading) {
        detailsViewModel.setDetails(article)
    }

    Scaffold { padding ->
        Column(
            modifier =
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            with(state) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop,
                    modifier =
                    Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
                Text(
                    text = title, style = MaterialTheme.typography.headlineMedium.copy(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )
                )
                Text(text = byline, style = MaterialTheme.typography.bodyMedium)
                Text(text = abstract, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
