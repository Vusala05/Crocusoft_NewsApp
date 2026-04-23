package com.example.newsapp_jetpackcompose.core

import android.graphics.drawable.Icon
import com.example.newsapp_jetpackcompose.ui.navigation.AppRoutes

data class BottomSheetNavigation(
    val route : AppRoutes,
    val selectedIcon : Int,
    val unSelectedIcon : Int
) {
    companion object{
        val navigations = listOf(
            BottomSheetNavigation(
                route = AppRoutes.Home,
                selectedIcon = Drawables.home_save,
                unSelectedIcon = Drawables.home
            ),
            BottomSheetNavigation(
                route = AppRoutes.MyList,
                selectedIcon = Drawables.bookmark_save,
                unSelectedIcon = Drawables.bookmark
        )
        )
    }
}