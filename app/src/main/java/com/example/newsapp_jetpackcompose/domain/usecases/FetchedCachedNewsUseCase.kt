package com.example.newsapp_jetpackcompose.domain.usecases

import com.example.newsapp_jetpackcompose.core.ContentState
import com.example.newsapp_jetpackcompose.core.constants.AppErrors
import com.example.newsapp_jetpackcompose.domain.repository.CacheRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import javax.inject.Inject

class FetchedCachedNewsUseCase @Inject constructor (val cacheRepository: CacheRepository) {
    operator fun invoke(url : String) : ContentState<NewsUiModel>{
        val news = cacheRepository.getNewsList(url)
       return if(news!=null){
           ContentState.Success(news)
        } else{
           ContentState.Error(AppErrors.noNewsDetail)
        }
    }

}