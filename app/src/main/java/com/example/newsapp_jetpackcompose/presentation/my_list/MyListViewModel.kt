package com.example.newsapp_jetpackcompose.presentation.my_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_jetpackcompose.domain.usecases.FetchBookMarkedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    val fetchBookMarkedNewsUseCase: FetchBookMarkedNewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MyListContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MyListContract.Effect>()
    val effect = _effect.asSharedFlow()

init {
  getToggledNews()
}

    fun getToggledNews(){
        viewModelScope.launch {
            fetchBookMarkedNewsUseCase().collect { news->
                _state.update { it.copy(bookMarkedList = news)
                        }
            }
                }
            }
        }
