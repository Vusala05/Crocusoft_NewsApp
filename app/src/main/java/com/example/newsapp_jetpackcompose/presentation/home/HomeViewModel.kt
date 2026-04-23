package com.example.newsapp_jetpackcompose.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_jetpackcompose.core.ContentState
import com.example.newsapp_jetpackcompose.domain.repository.CacheRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import com.example.newsapp_jetpackcompose.domain.usecases.FetchHeadlinesNewsUseCase
import com.example.newsapp_jetpackcompose.domain.usecases.FetchWorldNewsUseCase
import com.example.newsapp_jetpackcompose.domain.usecases.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val fetchHeadlinesNewsUseCase: FetchHeadlinesNewsUseCase,
    val cacheRepository: CacheRepository,
    val searchNewsUseCase: SearchNewsUseCase,
    val fetchWorldNewsUseCase: FetchWorldNewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect = _effect.asSharedFlow()
    private var searchJob : Job? =null
    val cachedNews = MutableStateFlow<List<NewsUiModel>>(emptyList())
    init {
        fetchTopHeadlines()
        fetchWorldNews()
    }


    fun onIntent(intent : HomeContract.Intent){
     when(intent){
         is HomeContract.Intent.OnSearchQueryChange -> {
             _state.update { it.copy(searchQuery = intent.query) }
             searchJob?.cancel()
             if (intent.query.isNotEmpty()) {
                 searchJob = viewModelScope.launch {
                     delay(500L)
                     _state.update { it.copy(isLoading = true) }
                     searchNews(intent.query)
                     _state.update { it.copy(isLoading = false) }

                 }
             } else {
                 _state.update { it.copy(
                     worldNews = cachedNews.value,
                     isLoading = false
                 ) }


             }
         }
     }

    }

    private fun getNewsList(useCase : suspend () -> ContentState<List<NewsUiModel>>,
                            onResponse : (List<NewsUiModel>) -> Unit) {
        viewModelScope.launch {
            when (val res = useCase.invoke()) {
                is ContentState.Error<*> -> {
                    Log.e("ERRORRR", res.message)

                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<NewsUiModel>> -> {
                    onResponse(res.data)
                }
            }
        }
    }

        private fun fetchTopHeadlines() {
            getNewsList(useCase = {fetchHeadlinesNewsUseCase()}) { topHeadlinesNews ->
                _state.update { it.copy(topHeadlinesNews = topHeadlinesNews) }
                cacheRepository.setNewsList(topHeadlinesNews)
                Log.e("TOP",topHeadlinesNews.size.toString())

            }

        }

        private fun fetchWorldNews() {
            getNewsList(useCase = {fetchWorldNewsUseCase()}) { worldNews ->
                _state.update { it.copy(worldNews = worldNews) }
                 cachedNews.update { worldNews }
                cacheRepository.setNewsList(worldNews)

            }
        }

        private fun searchNews(query: String) {
            getNewsList(useCase = {searchNewsUseCase(query)}) { searchedNews ->
                _state.update { it.copy(worldNews = searchedNews) }
                cacheRepository.setNewsList(searchedNews)

            }
        }

    }
