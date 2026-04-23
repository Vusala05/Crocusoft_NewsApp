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
         is HomeContract.Intent.OnNewsClick -> {


         }
         is HomeContract.Intent.OnLanguageSwitch -> {

         }
         is HomeContract.Intent.OnSearchQueryChange -> {
             _state.update { it.copy(searchQuery = intent.query) }
             searchJob?.cancel()
             if (intent.query.isNotEmpty()) {
                 searchJob = viewModelScope.launch {
                     delay(500L)
                     _state.update { it.copy(isLoading = true) }
                     searchMovie(intent.query)
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

    private fun fetchTopHeadlines(){
        viewModelScope.launch {
            when (val res = fetchHeadlinesNewsUseCase()) {
                is ContentState.Error<*> -> {
                    _effect.emit(HomeContract.Effect.ShowError("headlines news gelmir"))
                }

                is ContentState.Success<List<NewsUiModel>> -> {
                    _state.update {
                        it.copy(topHeadlinesNews = res.data)
                    }
                    cacheRepository.setNewsList(res.data)
                }
            }
        }
    }
    private fun fetchWorldNews(){
        viewModelScope.launch {
            when (val res = fetchWorldNewsUseCase()) {
                is ContentState.Error<*> -> {
                    _effect.emit(HomeContract.Effect.ShowError(res.message))
                }

                is ContentState.Success<List<NewsUiModel>> -> {
                    cachedNews.update { res.data }
                    _state.update {
                        it.copy(worldNews = res.data)
                    }
                    cacheRepository.setNewsList(res.data)
                }
            }
        }
    }
    private suspend fun searchMovie(query: String) {
        if (query.isBlank()) return
        when (val res = searchNewsUseCase(query)) {
            is ContentState.Error -> {
                _effect.emit(HomeContract.Effect.ShowError(res.message))
                _state.update { it.copy(worldNews = emptyList()) }
            }

            is ContentState.Success<List<NewsUiModel>> -> {
                _state.update {
                    cacheRepository.setNewsList(res.data)
                    it.copy(worldNews = res.data)
                }
            }
        }
    }

}