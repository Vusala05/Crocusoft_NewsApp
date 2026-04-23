package com.example.newsapp_jetpackcompose.data.repository

import android.util.Log
import com.example.newsapp_jetpackcompose.domain.repository.CacheRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor() : CacheRepository {

    var cachedMap = mutableMapOf<String, NewsUiModel>()
    override fun setNewsList(list: List<NewsUiModel>) {
        list.forEach { news->
         cachedMap[news.url] = news
        }
        Log.e("HEWSLIST : ", "${cachedMap.size}")
    }

    override fun getNewsList(url: String): NewsUiModel? {
        return cachedMap[url]

    }
}