package com.example.newsapp_jetpackcompose.domain.repository

import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel

interface CacheRepository {

    fun setNewsList (list: List<NewsUiModel>)

    fun getNewsList (url : String) : NewsUiModel?

}