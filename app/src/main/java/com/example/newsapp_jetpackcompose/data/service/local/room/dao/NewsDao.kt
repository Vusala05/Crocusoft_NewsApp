package com.example.newsapp_jetpackcompose.data.service.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp_jetpackcompose.data.service.local.room.model.NewsEntity
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsEntity)

    @Query("SELECT * FROM news")
    fun getBookmarkedNews(): Flow<List<NewsEntity>>


    @Query("SELECT EXISTS (SELECT 1 FROM news WHERE url = :url)")
    fun isBookmarked(url: String): Flow<Boolean>

    @Query("DELETE FROM news WHERE url = :url")
    suspend fun deleteByUrl(url: String)
}



