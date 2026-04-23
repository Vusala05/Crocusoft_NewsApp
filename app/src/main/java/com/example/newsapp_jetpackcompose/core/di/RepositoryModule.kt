package com.example.newsapp_jetpackcompose.core.di

import com.example.newsapp_jetpackcompose.data.repository.BookMarkRepositoryImpl
import com.example.newsapp_jetpackcompose.data.repository.CacheRepositoryImpl
import com.example.newsapp_jetpackcompose.data.repository.HomeRepositoryImpl
import com.example.newsapp_jetpackcompose.data.repository.LanguageRepositoryImpl
import com.example.newsapp_jetpackcompose.domain.repository.BookmarkRepository
import com.example.newsapp_jetpackcompose.domain.repository.CacheRepository
import com.example.newsapp_jetpackcompose.domain.repository.HomeRepository
import com.example.newsapp_jetpackcompose.domain.repository.LanguageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindCacheRepository(
        cacheRepositoryImpl: CacheRepositoryImpl
    ): CacheRepository

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookMarkRepositoryImpl: BookMarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    @Singleton
    abstract fun bindLanguageRepository(
        languageRepositoryImpl: LanguageRepositoryImpl
    ): LanguageRepository


}

