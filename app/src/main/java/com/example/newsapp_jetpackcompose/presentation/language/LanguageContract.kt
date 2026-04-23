package com.example.newsapp_jetpackcompose.presentation.language

import android.content.Context
import com.example.newsapp_jetpackcompose.core.language.AppLanguage

object LanguageContract {
    data class State(
        val languages : List<AppLanguage> = AppLanguage.entries,
        val selectedLanguageCode : String = "en",
        val isLoading : Boolean = false
    )
    sealed interface Intent {
        data class SelectLanguageCode(val context: Context,val code : String) : Intent
        data class  LoadCurrentLanguage(val context : Context) : Intent
    }

    sealed interface Effect{
        data class ShowError(val message : String) : Effect
    }
}