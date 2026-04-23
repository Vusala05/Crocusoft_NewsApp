package com.example.newsapp_jetpackcompose.core.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp_jetpackcompose.core.constants.RoomConstants
import com.example.newsapp_jetpackcompose.data.service.local.room.dao.NewsDao
import com.example.newsapp_jetpackcompose.data.service.local.room.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            RoomConstants.ROOM_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(db: NewsDatabase): NewsDao = db.getDao()
}