package com.example.newsapp_jetpackcompose.data.service.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp_jetpackcompose.data.service.local.room.dao.NewsDao
import com.example.newsapp_jetpackcompose.data.service.local.room.model.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getDao() : NewsDao
}