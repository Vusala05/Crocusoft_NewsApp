package com.example.newsapp_jetpackcompose.presentation.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun LanguageView(
    navController : NavController,
    viewModel: LanguageViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LanguageContent(
        postIntent = viewModel::onIntent,
        state = state,
        onNavigateBack = navController::popBackStack
    )



}