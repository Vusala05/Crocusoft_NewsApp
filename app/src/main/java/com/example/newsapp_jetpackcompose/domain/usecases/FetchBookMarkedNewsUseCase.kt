package com.example.newsapp_jetpackcompose.domain.usecases

import com.example.newsapp_jetpackcompose.domain.repository.BookmarkRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchBookMarkedNewsUseCase @Inject constructor(
    private val newsRepository: BookmarkRepository
) {
    operator fun invoke(): Flow<List<NewsUiModel>> {
        return newsRepository.getBookmarkedNews()
    }
}