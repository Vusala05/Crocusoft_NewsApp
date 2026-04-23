package com.example.newsapp_jetpackcompose.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.newsapp_jetpackcompose.core.ContentState
import com.example.newsapp_jetpackcompose.core.constants.AppErrors
import com.example.newsapp_jetpackcompose.data.mappers.toUiModel
import com.example.newsapp_jetpackcompose.data.service.remote.model.NewsResponseModel
import com.example.newsapp_jetpackcompose.data.service.remote.service.ApiService
import com.example.newsapp_jetpackcompose.domain.repository.HomeRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val apiService: ApiService) : HomeRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun fetchTopHeadlines(): ContentState<List<NewsUiModel>> {
        return safeApiCall { apiService.fetchTopHeadlines() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun fetchWorldNews(): ContentState<List<NewsUiModel>> {
        return safeApiCall { apiService.fetchWorldNews() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun searchNews(q: String): ContentState<List<NewsUiModel>> {
        return safeApiCall { apiService.searchNews(q) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun safeApiCall(apiRequest : suspend() -> NewsResponseModel): ContentState<List<NewsUiModel>> =
        try {
            val articles = apiRequest().articles
            if (articles.isEmpty()) {
                ContentState.Error(AppErrors.noNews)
            } else {
                val uiList = articles.map { it.toUiModel() }
                ContentState.Success(uiList)
            }
        } catch (e: Exception){
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }



}


