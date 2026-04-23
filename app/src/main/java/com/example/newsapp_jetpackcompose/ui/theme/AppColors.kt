package com.example.newsapp_jetpackcompose.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColors = compositionLocalOf<AppColors> {
    error("No colors provided")
}

data class AppColors(
 val background : Color,
 val onBackgroundText : Color,//esas text
 val primaryText : Color,//boz textler
 val searchBar : Color,
 val secondary : Color,//cardlar
 val onSecondary : Color,//qirmizi noqte,
 val authColor : Color,//qirmizi noqte,
 val readMoreColor : Color,//qirmizi noqte,
 val navigationColor : Color,//qirmizi noqte,
 val navIconColor : Color,//qirmizi noqte,
 val secondaryText : Color,//qirmizi noqte,
 val selectedItem : Color,//qirmizi noqte,



)

    val darkColors = AppColors(
        background = DarkBackground,
        onBackgroundText = DarkPrimaryText,
        primaryText = DarkPrimaryText,
        searchBar = DarkSearchBar,
        secondary = DarkSurface,
        onSecondary = ActionColor,
        authColor = DarkAuthName,
        readMoreColor = DarkReadMore,
        navigationColor = DarkNavigationColor,
        navIconColor = DarkIconColor,
        secondaryText = DarkSecondaryText,
        selectedItem = SelectedItemColor
    )
    val lightColors = AppColors(
       background = LightBackground,
        onBackgroundText = LightPrimaryText,
        primaryText = LightPrimaryText,
        searchBar = LightSearchBar,
        secondary = LightSurface,
        onSecondary = ActionColor,
        authColor = LightAuthName,
        readMoreColor = LightReadMore,
        navigationColor = LightNavigationColor,
        navIconColor = LightIconColor,
        secondaryText = LightSecondaryText,
        selectedItem = SelectedItemColor
    )
