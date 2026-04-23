package com.example.newsapp_jetpackcompose.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp_jetpackcompose.core.BaseTheme
import com.example.newsapp_jetpackcompose.core.BottomSheetNavigation
import com.example.newsapp_jetpackcompose.ui.navigation.AppRoutes
import com.example.newsapp_jetpackcompose.ui.navigation.MainRoutes
import kotlin.math.log

@Composable
fun App() {
    val colors = LocalColors.current
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route


    val hiddenRoutes  = listOf(
        AppRoutes.NewsDetail::class.qualifiedName,
        AppRoutes.Language::class.qualifiedName
    )
    val currentBaseRoute = currentRoute?.substringBefore("/")

    Log.e("current route -> ", currentBaseRoute ?:"")
    Log.e("detail route", AppRoutes.NewsDetail::class.qualifiedName ?:"")
    Scaffold(
        bottomBar = {
         if(currentBaseRoute !in hiddenRoutes){

             Surface(
                 color = Color.Transparent,
                 tonalElevation = 0.dp,
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(BaseTheme.dimens.navigationHeight)
             ) {
                 NavigationBar(
                     containerColor = colors.navigationColor
                 ) {
                     BottomSheetNavigation.navigations.forEach { route ->

                         val isSelected = route.route::class.qualifiedName == currentRoute

                         NavigationBarItem(
                             selected = isSelected,
                             onClick = {
                                 if (!isSelected) {
                                     navController.navigate(route.route)
                                 }
                             },
                             icon = {
                                 Icon(
                                     painter = if (isSelected)
                                         painterResource(route.selectedIcon)
                                     else
                                         painterResource(route.unSelectedIcon),
                                     contentDescription = null,
                                     tint = colors.navIconColor,
                                     modifier = Modifier.size(BaseTheme.dimens.iconSize)
                                 )
                             },
                             colors = NavigationBarItemDefaults.colors(
                                 indicatorColor = Color.Transparent
                             )
                         )
                     }
                 }
             }
         }
        },
    ) { paddingValues ->
        val contentPadding =
            if(currentRoute == AppRoutes.NewsDetail::class.qualifiedName){
                PaddingValues(0.dp)
            }
        else{
               paddingValues
            }
        MainRoutes(
            navController = navController,
            paddingValues = contentPadding,
        )

    }




}