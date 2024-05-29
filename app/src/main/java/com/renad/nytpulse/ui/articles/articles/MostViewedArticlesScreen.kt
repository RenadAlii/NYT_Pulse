package com.renad.nytpulse.ui.articles.articles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.renad.nytpulse.R
import com.renad.nytpulse.domain.enities.Article
import com.renad.nytpulse.ui.components.ErrorSnackbar
import com.renad.nytpulse.ui.components.shimmerLoadingAnimation
import kotlinx.coroutines.launch

@Composable
fun MostViewedArticlesScreen(
    modifier: Modifier = Modifier,
    mostViewedArticlesViewModel: MostViewedArticlesViewModel = hiltViewModel(),
    navigateToDetailsScreen: (article: Article) -> Unit,
) {
    val state by mostViewedArticlesViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Handle Snackbar error messages
    state.errorMsg?.handel {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = it,
                withDismissAction = true,
                duration = SnackbarDuration.Short,
            )
        }
    }

    if (!state.isArticleLoaded) mostViewedArticlesViewModel.loadArticles()

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier.padding(24.dp),
                text = stringResource(id = R.string.app_bar_title),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
        },
        snackbarHost = {
            Box(modifier = Modifier.fillMaxSize()) {
                ErrorSnackbar(
                    snackbarHostState = snackbarHostState,
                    onDismiss = {
                        snackbarHostState.currentSnackbarData?.dismiss()
                    },
                    modifier = modifier.align(Alignment.TopCenter),
                )
            }
        },
        content = { contentPadding ->
            Column(modifier = Modifier.padding(contentPadding)) {
                AnimatedVisibility(visible = state.isLoading) {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        ShimmerLoadingAnimation()
                        ShimmerLoadingAnimation()
                        ShimmerLoadingAnimation()
                        ShimmerLoadingAnimation()
                    }
                }
                AnimatedVisibility(visible = state.articles.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        itemsIndexed(
                            items = state.articles,
                            key = { _, item -> item.id }) { index, item ->
                            ArticleItem(article = item, onClickItem = navigateToDetailsScreen)
                            if (index < state.articles.lastIndex) {
                                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                            }
                        }
                    }
                }
            }
        },
    )
}

@Composable
private fun ArticleItem(
    article: Article,
    onClickItem: (article: Article) -> Unit,
) {
    Row(
        modifier =
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClickItem(article)
            },
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier =
            Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(),
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    lineBreak = LineBreak.Paragraph,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 21.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = article.abstract,
                style = MaterialTheme.typography.bodySmall.copy(
                    lineBreak = LineBreak.Paragraph,
                    color = Color.Gray,
                    lineHeight = 21.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = article.byline,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun ShimmerLoadingAnimation() {
    Row(
        modifier =
        Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Box(
            modifier =
            Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .background(color = Color.LightGray)
                .size(100.dp)
                .shimmerLoadingAnimation(),
        )
        Column(
            modifier =
            Modifier
                .padding(12.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Box(
                modifier =
                Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.LightGray)
                    .size(height = 30.dp, width = 200.dp)
                    .shimmerLoadingAnimation(),
            )
            Box(
                modifier =
                Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.LightGray)
                    .size(height = 30.dp, width = 100.dp)
                    .shimmerLoadingAnimation(),
            )
        }
    }
}
