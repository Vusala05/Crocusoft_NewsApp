package com.example.newsapp_jetpackcompose.domain.repository

import com.example.newsapp_jetpackcompose.data.service.local.room.model.NewsEntity
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    suspend fun insertNews(news: NewsUiModel)

    fun getBookmarkedNews(): Flow<List<NewsUiModel>>

    fun isBookmarked(url: String): Flow<Boolean>

    suspend fun deleteByUrl(url: String)
}



