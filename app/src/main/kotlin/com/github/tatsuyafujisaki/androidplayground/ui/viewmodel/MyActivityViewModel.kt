package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import android.app.Activity
import android.content.res.Configuration
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.tatsuyafujisaki.androidplayground.domain.MyRealmRepository
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
class MyActivityViewModel @Inject constructor(
    myRealmRepository: MyRealmRepository,
) : ViewModel() {
    private val _orientation2 = MutableStateFlow(Orientation2.PORTRAIT)
    val orientation2: StateFlow<Orientation2> = _orientation2.asStateFlow()

    private val _orientation4 = MutableStateFlow(Orientation4.PORTRAIT)
    val orientation4: StateFlow<Orientation4> = _orientation4.asStateFlow()

    val orientation2b: Flow<Orientation2> = orientation4.map(Orientation2::create)

    private val _myLiveData = MutableLiveData("")
    val myLiveData: LiveData<String> = _myLiveData

    /**
     * Call setOrientation(resources.configuration.orientation) in [Activity.onCreate]
     */
    @Deprecated(
        message = "Not recommended for production use because [Activity.onCreate] is not always " + "called when you change the orientation from or to the reverse portrait.",
        replaceWith = ReplaceWith("setOrientation4"),
    )
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

    fun setMyLiveData(something: String) {
        _myLiveData.value = something
    }

    companion object {
        private const val TAG = "MyActivityViewModel"
    }
}
