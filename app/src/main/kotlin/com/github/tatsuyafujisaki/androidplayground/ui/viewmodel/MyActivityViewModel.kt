package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import android.app.Activity
import android.content.res.Configuration
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.tatsuyafujisaki.androidplayground.data.enum.Orientation2
import com.github.tatsuyafujisaki.androidplayground.data.enum.Orientation4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyActivityViewModel : ViewModel() {
    private val _orientation2 = MutableStateFlow(Orientation2.PORTRAIT)
    val orientation2: StateFlow<Orientation2> = _orientation2.asStateFlow()

    private val _orientation4 = MutableStateFlow(Orientation4.PORTRAIT)
    val orientation4: StateFlow<Orientation4> = _orientation4.asStateFlow()

    private val _stateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    private val _myLiveData = MutableLiveData("")
    val myLiveData: LiveData<String> = _myLiveData

    /**
     * Call setOrientation(resources.configuration.orientation) in [Activity.onCreate]
     */
    fun setOrientation2(orientation: Int) {
        _orientation2.value = when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> Orientation2.PORTRAIT
            Configuration.ORIENTATION_LANDSCAPE -> Orientation2.LANDSCAPE
            else -> error("Unknown orientation: $orientation")
        }
    }

    /**
     * Call the following in [Activity.onCreate]
     *
     * object : OrientationEventListener(this) {
     *     override fun onOrientationChanged(orientation: Int) {
     *         viewModel.setOrientation4(orientation)
     *     }
     * }.enable()
     */
    fun setOrientation4(orientation: Int) {
        _orientation4.value = when (orientation) {
            0 -> Orientation4.PORTRAIT
            90 -> Orientation4.LANDSCAPE
            180 -> Orientation4.REVERSE_PORTRAIT
            270 -> Orientation4.REVERSE_LANDSCAPE
            else -> error("Unknown orientation: $orientation")
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
