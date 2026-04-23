package com.example.newsapp_jetpackcompose.presentation.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp_jetpackcompose.core.language.LanguageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LanguageContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LanguageContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: LanguageContract.Intent) {
        when (intent) {
            is LanguageContract.Intent.SelectLanguageCode -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    delay(500)
                    LanguageManager.changeLanguage(intent.context, intent.code)
                    updateLanguageState(intent.code, isLoading = false)
                }
            }
            is LanguageContract.Intent.LoadCurrentLanguage -> {
                val currentCode = LanguageManager.getCurrentLanguage(intent.context)
                updateLanguageState(currentCode)
            }
        }
    }

    private fun updateLanguageState(code: String, isLoading: Boolean = false) {
        _state.update {
            it.copy(
                selectedLanguageCode = code,
                isLoading = isLoading
            )
        }
    }

}