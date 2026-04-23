package com.example.newsapp_jetpackcompose.domain.usecases

import com.example.newsapp_jetpackcompose.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckBookmarkedStatusUseCase @Inject constructor(
    private val newsRepository: BookmarkRepository
) {
    operator fun invoke(url : String): Flow<Boolean> {
        return newsRepository.isBookmarked(url)
    }
}