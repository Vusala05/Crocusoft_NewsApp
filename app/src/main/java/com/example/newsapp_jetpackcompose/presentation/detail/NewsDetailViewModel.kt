package com.example.newsapp_jetpackcompose.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_jetpackcompose.core.ContentState
import com.example.newsapp_jetpackcompose.core.constants.AppErrors
import com.example.newsapp_jetpackcompose.domain.repository.CacheRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import com.example.newsapp_jetpackcompose.domain.usecases.AddCachedNewsUseCase
import com.example.newsapp_jetpackcompose.domain.usecases.CheckBookmarkedStatusUseCase
import com.example.newsapp_jetpackcompose.domain.usecases.FetchedCachedNewsUseCase
import com.example.newsapp_jetpackcompose.domain.usecases.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    val fetchedCachedNewsUseCase: FetchedCachedNewsUseCase,
    val checkBookmarkedStatusUseCase: CheckBookmarkedStatusUseCase,
    val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(NewsDetailContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<NewsDetailContract.Effect>()
    val effect = _effect.asSharedFlow()

     fun onIntent(intent : NewsDetailContract.Intent){
        when(intent){
            is NewsDetailContract.Intent.LoadSelectedNewsData ->{
             loadNewsDetail(intent.url)
                toggleStatusCheck(intent.url)
            }

            is NewsDetailContract.Intent.ToggleSaveBtn ->{
                toggleBookmark(intent.news)
            }
        }
    }

    private fun loadNewsDetail(url:String){
        when(val res = fetchedCachedNewsUseCase(url)){
            is ContentState.Success ->{
                _state.update { it.copy(selectedNews = res.data) }
            }
            is ContentState.Error ->{
                NewsDetailContract.Effect.ShowError(res.message)
            }
        }
    }
    private fun toggleStatusCheck(url : String){
        viewModelScope.launch {
            checkBookmarkedStatusUseCase(url).collect { isBookmarked ->
                _state.update { it.copy(isBookmarked = isBookmarked)}
            }
        }
    }

    private fun toggleBookmark(news : NewsUiModel){
        viewModelScope.launch {
            val currentSate = _state.value
            toggleBookmarkUseCase(news = news, isBookmarked = currentSate.isBookmarked)
        }
    }



}