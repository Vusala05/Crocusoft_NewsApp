package com.example.newsapp_jetpackcompose.data.service.remote.service

import com.example.newsapp_jetpackcompose.data.service.remote.model.NewsResponseModel

interface ApiService {

    suspend fun fetchTopHeadlines() : NewsResponseModel

    suspend fun fetchWorldNews() : NewsResponseModel

    suspend fun searchNews(q : String) : NewsResponseModel
}