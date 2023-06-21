package com.github.tatsuyafujisaki.androidplayground.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _something = MutableLiveData("")
    val something: LiveData<String> = _something

    fun setSomething(something: String) {
        _something.value = something
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, object {}.javaClass.enclosingMethod!!.name)
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
