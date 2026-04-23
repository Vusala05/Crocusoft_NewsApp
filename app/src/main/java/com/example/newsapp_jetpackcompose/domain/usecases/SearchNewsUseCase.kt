package com.example.newsapp_jetpackcompose.domain.usecases

import com.example.newsapp_jetpackcompose.core.ContentState
import com.example.newsapp_jetpackcompose.domain.repository.HomeRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(val homeRepository: HomeRepository) {

    suspend operator fun invoke(q: String) : ContentState<List<NewsUiModel>>{
        return homeRepository.searchNews(q)
    }
}
