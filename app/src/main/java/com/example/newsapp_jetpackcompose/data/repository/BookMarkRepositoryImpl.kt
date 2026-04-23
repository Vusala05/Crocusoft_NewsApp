package com.example.newsapp_jetpackcompose.data.repository
import android.util.Log
import com.example.newsapp_jetpackcompose.data.mappers.toEntity
import com.example.newsapp_jetpackcompose.data.mappers.toUiModel
import com.example.newsapp_jetpackcompose.data.service.local.room.dao.NewsDao
import com.example.newsapp_jetpackcompose.data.service.local.room.model.NewsEntity
import com.example.newsapp_jetpackcompose.domain.repository.BookmarkRepository
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookMarkRepositoryImpl @Inject constructor(val dao: NewsDao) : BookmarkRepository {

    override suspend fun insertNews(news: NewsUiModel) {
        val entity = news.toEntity()
        Log.e("INSERT",entity.title)
        dao.insertNews(entity)
    }

    override fun getBookmarkedNews(): Flow<List<NewsUiModel>> {
        return dao.getBookmarkedNews()
            .map { list ->
                list.map { it.toUiModel() }
            }
    }
    override fun isBookmarked(url: String): Flow<Boolean> = dao.isBookmarked(url)

    override suspend fun deleteByUrl(url: String) = dao.deleteByUrl(url)


}

