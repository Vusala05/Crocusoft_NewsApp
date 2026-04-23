package com.example.newsapp_jetpackcompose.presentation.my_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp_jetpackcompose.ui.navigation.AppRoutes

@Composable
fun MyListView(
    navController : NavController,
    viewModel: MyListViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    MyListContent(
        state = state
    ) {
        navController.navigate(AppRoutes.NewsDetail(it))
    }

}