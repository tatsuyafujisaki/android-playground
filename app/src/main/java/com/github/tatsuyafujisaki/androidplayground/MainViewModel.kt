package com.github.tatsuyafujisaki.androidplayground

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val liveData = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
