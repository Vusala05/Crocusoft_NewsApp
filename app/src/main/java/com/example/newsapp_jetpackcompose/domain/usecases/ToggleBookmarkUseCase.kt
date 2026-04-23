package com.example.newsapp_jetpackcompose.domain.usecases

import com.example.newsapp_jetpackcompose.domain.repository.BookmarkRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(news: NewsUiModel, isBookmarked: Boolean) {
        if (isBookmarked) {
            repository.deleteByUrl(news.url)
        } else {
            repository.insertNews(news)

        }
    }
}