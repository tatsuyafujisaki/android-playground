package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.tatsuyafujisaki.androidplayground.enum.Orientation2
import com.github.tatsuyafujisaki.androidplayground.enum.Orientation4
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

@HiltViewModel
class MyActivityViewModel @Inject constructor() : ViewModel() {
    private val _orientation2 = MutableStateFlow(Orientation2.PORTRAIT)
    val orientation2: StateFlow<Orientation2> = _orientation2.asStateFlow()

    private val _orientation4 = MutableStateFlow(Orientation4.PORTRAIT)
    val orientation4: StateFlow<Orientation4> = _orientation4.asStateFlow()

    val orientation2b: Flow<Orientation2> = orientation4.map(Orientation2::create)

    private val _myLiveData = MutableLiveData("")
    val myLiveData: LiveData<String> = _myLiveData

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
        when (orientation) {
            0 -> Orientation4.PORTRAIT
            90 -> Orientation4.LANDSCAPE
            180 -> Orientation4.REVERSE_PORTRAIT
            270 -> Orientation4.REVERSE_LANDSCAPE
            else -> null
        }?.let {
            _orientation4.value = it
        }
    }

    fun setMyLiveData(something: String) {
        _myLiveData.value = something
    }
}
