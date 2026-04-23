package com.example.newsapp_jetpackcompose.data.service.local

import android.content.Context
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(
    name = "bookmarks"
)
val BOOKMARKED_NEWS_URL = stringSetPreferencesKey("bookmarked_news_url")

