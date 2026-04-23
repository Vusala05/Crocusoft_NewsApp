package com.example.newsapp_jetpackcompose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.newsapp_jetpackcompose.data.service.local.LANGUAGE_CODE
import com.example.newsapp_jetpackcompose.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(val dataStore: DataStore<Preferences>) : LanguageRepository {
    override suspend fun addLanguageCode(code: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE_CODE] = code
        }
    }

    override  fun observeLanguageCode(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[LANGUAGE_CODE] ?: "EN" }

    }



}
