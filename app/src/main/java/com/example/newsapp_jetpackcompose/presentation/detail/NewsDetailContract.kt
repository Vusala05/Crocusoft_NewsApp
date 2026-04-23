package com.example.newsapp_jetpackcompose.presentation.detail

import android.graphics.Movie
import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel

object NewsDetailContract {

    sealed interface Intent {
        data object ToggleSettings : Intent
        data class LoadSelectedNewsData(val url: String) : Intent
        data object ClickReadMore : Intent
        data class ToggleSaveBtn(val news : NewsUiModel) : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
        data class OpenWebBrowser(val url: String) : Effect
        data object NavigateBack : Effect
    }

    data class State(
        val selectedNews: NewsUiModel = NewsUiModel.empty,
        val isSettingsToggle: Boolean = false,
        val isBookmarked : Boolean = false,
    )
}