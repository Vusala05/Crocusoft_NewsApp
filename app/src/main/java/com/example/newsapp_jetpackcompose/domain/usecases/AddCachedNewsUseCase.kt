package com.example.newsapp_jetpackcompose.domain.usecases

import com.example.newsapp_jetpackcompose.domain.repository.CacheRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import javax.inject.Inject

class AddCachedNewsUseCase @Inject constructor (val cacheRepository: CacheRepository) {
    operator fun invoke(news : List<NewsUiModel>){
        return cacheRepository.setNewsList(news)
        }
    }

