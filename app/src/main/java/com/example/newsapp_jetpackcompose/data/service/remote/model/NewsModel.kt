package com.example.newsapp_jetpackcompose.data.service.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsModel(
    val source: SourceModel?=null,
    val author: String?= null,
    val title: String? = null,
    val description: String? = null,
    val url: String?=null,
    val urlToImage: String?=null,
    val publishedAt: String?=null,
    val content: String?=null
) {
}




