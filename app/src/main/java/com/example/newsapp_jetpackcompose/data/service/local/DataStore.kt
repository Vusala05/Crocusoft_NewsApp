package com.example.newsapp_jetpackcompose.data.service.local

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(
    name = "language"
)
val LANGUAGE_CODE = stringPreferencesKey("language_code")

