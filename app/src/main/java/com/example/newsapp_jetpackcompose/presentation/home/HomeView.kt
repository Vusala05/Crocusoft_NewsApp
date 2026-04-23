package com.example.newsapp_jetpackcompose.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp_jetpackcompose.ui.navigation.AppRoutes

@Composable
fun HomeView(
   navController : NavController,
   viewModel: HomeViewModel = hiltViewModel()
){
   val state by viewModel.state.collectAsStateWithLifecycle()
   HomeContent(
      postIntent = viewModel::onIntent,
      state = state,
      effect = viewModel.effect,
      onNavigateDetail = {
         navController.navigate(AppRoutes.NewsDetail(it))},
      onNavigateLanguage = {
         navController.navigate(AppRoutes.Language)
      }
   )

}
