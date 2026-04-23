package com.example.newsapp_jetpackcompose.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.newsapp_jetpackcompose.presentation.detail.NewsDetailView
import com.example.newsapp_jetpackcompose.presentation.home.HomeView
import com.example.newsapp_jetpackcompose.presentation.language.LanguageView
import com.example.newsapp_jetpackcompose.presentation.my_list.MyListView

@Composable
fun MainRoutes(
   navController : NavHostController,
   paddingValues: PaddingValues
){
    NavHost(
        startDestination = AppRoutes.Home,
        navController=navController,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<AppRoutes.Home>{
            HomeView(navController = navController)
        }
        composable<AppRoutes.NewsDetail>{ backStackEntry ->
            val args = backStackEntry.toRoute<AppRoutes.NewsDetail>()
            NewsDetailView(navController = navController, newsUrl = args.url)
        }
        composable<AppRoutes.Language>{
            LanguageView(navController = navController)
        }
        composable<AppRoutes.MyList>{
            MyListView(navController = navController)
        }

    }

}