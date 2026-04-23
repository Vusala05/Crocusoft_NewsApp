package com.example.newsapp_jetpackcompose.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_jetpackcompose.domain.repository.CacheRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import com.example.newsapp_jetpackcompose.domain.usecases.CheckBookmarkedStatusUseCase
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
    val cacheRepository: CacheRepository,
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
               val selectedNews = cacheRepository.getNewsList(intent.url)
                if(selectedNews!=null){
                    _state.update { it.copy(selectedNews = selectedNews ) }
                }
                 else{
                    Log.e("url bosdur : ", "bossssssdurrrr")
                }
                toggleStatusCheck(intent.url)
            }
            is NewsDetailContract.Intent.ClickReadMore ->{

            }
            is NewsDetailContract.Intent.ToggleSettings ->{

            }
            is NewsDetailContract.Intent.ToggleSaveBtn ->{
                toggleBookmark(intent.news)
            }
        }
    }
    private fun toggleStatusCheck(url : String){
        viewModelScope.launch {
            checkBookmarkedStatusUseCase(url).collect { isBookmarked ->
                Log.e("isBookMarked","$isBookmarked")
                _state.update { it.copy(isBookmarked = isBookmarked)}
            }
        }
    }

    private fun toggleBookmark(news : NewsUiModel){
        viewModelScope.launch {
            val currentSate = _state.value
            Log.e("news","${news.url}, ${news.author}")
            Log.e("status","${currentSate.isBookmarked}")
            toggleBookmarkUseCase(news = news, isBookmarked = currentSate.isBookmarked)
        }
    }



}