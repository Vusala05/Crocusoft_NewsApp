package com.example.newsapp_jetpackcompose.data.service.remote.service

import com.example.newsapp_jetpackcompose.core.constants.ApiConstants
import com.example.newsapp_jetpackcompose.data.service.remote.model.NewsResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class KtorService @Inject constructor(
    val httpClient: HttpClient) : ApiService {
    override suspend fun fetchTopHeadlines(): NewsResponseModel {
        return httpClient.get(ApiConstants.TOP_HEADLINES){
            parameter("country","us")
            parameter("page", 1)
        }.body()
    }

    override suspend fun fetchWorldNews(): NewsResponseModel {
        return httpClient.get(ApiConstants.SEARCH_NEWS){
            parameter("q", "world")
            parameter("sortBy","popularity")
            parameter("page", 1)
        }.body()
    }

    override suspend fun searchNews(q: String): NewsResponseModel {
        return httpClient.get(ApiConstants.SEARCH_NEWS){
            parameter("q", q)
            parameter("page", 1)
        }.body()
    }


}