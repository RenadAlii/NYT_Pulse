package com.renad.nytpulse.ui.articles.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renad.nytpulse.core.di.IoDispatchers
import com.renad.nytpulse.core.utils.SingleEvent
import com.renad.nytpulse.core.utils.handleNetworkThrowable
import com.renad.nytpulse.domain.usecase.GetMostViewedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MostViewedArticlesViewModel
@Inject
constructor(
    private val useCase: GetMostViewedArticlesUseCase,
    @IoDispatchers private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MostViewedArticlesState())
    val uiState get() = _uiState.asStateFlow()

    fun loadArticles(period: String = listOf("7", "30", "1").random()) {
        useCase.invoke(period).onStart {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
        }.map {
            _uiState.update { state ->
                state.copy(articles = it, isLoading = false, isArticleLoaded = true)
            }
        }.catch { error ->
            _uiState.update { state ->
                state.copy(
                    errorMsg = SingleEvent(error.handleNetworkThrowable()),
                    isLoading = false
                )
            }
        }.flowOn(ioDispatcher).launchIn(viewModelScope)
    }
}
