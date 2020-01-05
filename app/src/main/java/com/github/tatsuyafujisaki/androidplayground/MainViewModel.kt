package com.github.tatsuyafujisaki.androidplayground

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val tag = this::class.java.simpleName
    val liveData = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        Log.d(tag, object {}.javaClass.enclosingMethod!!.name)
    }
}
