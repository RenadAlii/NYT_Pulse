package com.renad.nytpulse.ui.articles.details

import androidx.lifecycle.ViewModel
import com.renad.nytpulse.domain.enities.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
@Inject
constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(DetailsState())
    val uiState get() = _uiState.asStateFlow()

    fun setDetails(article: Article) {
        _uiState.update {
            it.copy(
                isLoading = false,
                title = article.title,
                abstract = article.abstract,
                byline = article.byline,
                imageUrl = article.imageUrl,
            )
        }
    }
}
