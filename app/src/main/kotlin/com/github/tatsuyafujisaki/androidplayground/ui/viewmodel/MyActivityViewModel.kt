package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyActivityViewModel @Inject constructor() : ViewModel() {
    private val _myLiveData = MutableLiveData("")
    val myLiveData: LiveData<String> = _myLiveData

    fun setMyLiveData(something: String) {
        _myLiveData.value = something
    }
}
