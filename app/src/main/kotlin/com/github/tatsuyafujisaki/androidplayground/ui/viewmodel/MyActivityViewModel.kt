package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import android.app.Activity
import android.content.res.Configuration
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.tatsuyafujisaki.androidplayground.data.enum.Orientation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyActivityViewModel : ViewModel() {
    private val _orientation = MutableStateFlow(Orientation.PORTRAIT)
    val orientation: StateFlow<Orientation> = _orientation.asStateFlow()

    private val _stateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    private val _myLiveData = MutableLiveData("")
    val myLiveData: LiveData<String> = _myLiveData

    /**
     * Call setOrientation(resources.configuration.orientation) in [Activity.onCreate]
     */
    fun setOrientation(orientation: Int) {
        _orientation.value = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Orientation.LANDSCAPE
        } else {
            Orientation.PORTRAIT
        }
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
