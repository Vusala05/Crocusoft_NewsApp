package com.example.newsapp_jetpackcompose.data.service.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseModel(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsModel>
)