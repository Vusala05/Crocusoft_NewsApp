package com.example.newsapp_jetpackcompose.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {

     @Serializable
     data object Home : AppRoutes

      @Serializable
      data class NewsDetail(val url : String) : AppRoutes

      @Serializable
      data object Language : AppRoutes
      @Serializable
      data object MyList : AppRoutes
}