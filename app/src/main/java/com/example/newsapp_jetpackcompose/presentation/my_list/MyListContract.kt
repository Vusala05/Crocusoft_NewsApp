package com.example.newsapp_jetpackcompose.presentation.my_list

import com.example.newsapp_jetpackcompose.domain.uimodel.NewsUiModel

object MyListContract {

    sealed interface Intent{

    }

    sealed interface Effect {

    }

    data class State(
        val bookMarkedList : List<NewsUiModel> = emptyList()
    )
}