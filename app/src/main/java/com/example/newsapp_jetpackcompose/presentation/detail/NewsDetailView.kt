package com.example.newsapp_jetpackcompose.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun NewsDetailView(
    navController : NavController,
    newsUrl: String,
    viewModel: NewsDetailViewModel = hiltViewModel()
){
    LaunchedEffect(Unit) {
        viewModel.onIntent(NewsDetailContract.Intent.LoadSelectedNewsData(url = newsUrl))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    NewsDetailContent(
      state = state,
        postIntent = viewModel::onIntent,
        effect = viewModel.effect,
        onNavigateBack = navController::popBackStack
    )

}