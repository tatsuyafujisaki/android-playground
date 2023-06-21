package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    private val _myLiveData = MutableLiveData("")
    val myLiveData: LiveData<String> = _myLiveData

    fun setMyStateFlow(something: String) {
        _stateFlow.value = something
    }

    fun setMyLiveData(something: String) {
        _myLiveData.value = something
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
