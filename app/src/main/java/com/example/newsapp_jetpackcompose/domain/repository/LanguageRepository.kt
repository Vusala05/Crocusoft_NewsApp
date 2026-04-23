package com.example.newsapp_jetpackcompose.domain.repository

import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    suspend fun addLanguageCode(code : String)

    fun observeLanguageCode() : Flow<String>
}