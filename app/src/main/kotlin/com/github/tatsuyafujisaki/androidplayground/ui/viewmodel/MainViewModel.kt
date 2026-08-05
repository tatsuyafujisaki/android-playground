package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    fun setMyStateFlow(something: String) {
        _stateFlow.value = something
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
