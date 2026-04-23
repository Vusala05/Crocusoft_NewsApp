package com.example.newsapp_jetpackcompose.presentation.my_list

import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel

object MyListContract {

    sealed interface Effect {
        data class ShowError(val message : String)
    }

    data class State(
        val bookMarkedList : List<NewsUiModel> = emptyList()
    )
}