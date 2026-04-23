package com.example.newsapp_jetpackcompose.presentation.home

import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel

object HomeContract {

    data class State(
        val topHeadlinesNews: List<NewsUiModel> = emptyList(),
        val worldNews: List<NewsUiModel> = emptyList(),
        val searchQuery: String = "",
        val error: String? = null,
        val isLoading : Boolean = false
    )

        sealed class Intent {
            data class OnSearchQueryChange(val query: String) : Intent()
            data class OnNewsClick(val url : String) : Intent()
            object OnLanguageSwitch : Intent()
        }

        sealed class Effect {
            data class ShowError(val message: String) : Effect()
        }
}