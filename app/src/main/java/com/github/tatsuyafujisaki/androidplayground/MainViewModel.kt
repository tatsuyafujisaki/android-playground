package com.github.tatsuyafujisaki.androidplayground

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _liveData = MutableLiveData("")
    val liveData: LiveData<String>
        get() = _liveData

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
