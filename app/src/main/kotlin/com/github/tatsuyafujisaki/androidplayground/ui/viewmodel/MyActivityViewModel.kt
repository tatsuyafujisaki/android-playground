package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.app.Activity

class MyActivityViewModel : ViewModel() {
    private val _orientation = MutableStateFlow(-1)
    val orientation: StateFlow<Int> = _orientation.asStateFlow()

    private val _stateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    private val _myLiveData = MutableLiveData("")
    val myLiveData: LiveData<String> = _myLiveData

    /**
     * Call setOrientation(resources.configuration.orientation) in [Activity.onCreate]
     */
    fun setOrientation(orientation: Int) {
        _orientation.value = orientation
    }

    fun setMyStateFlow(something: String) {
        _stateFlow.value = something
    }

    fun setMyLiveData(something: String) {
        _myLiveData.value = something
    }

    companion object {
        private const val TAG = "MyActivityViewModel"
    }
}
