package com.example.newsapp_jetpackcompose.domain.repository

import com.example.newsapp_jetpackcompose.core.ContentState
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel

interface HomeRepository {

    suspend fun fetchTopHeadlines() : ContentState<List<NewsUiModel>>

    suspend fun fetchWorldNews () : ContentState<List<NewsUiModel>>

    suspend fun searchNews(q : String) : ContentState<List<NewsUiModel>>
}
