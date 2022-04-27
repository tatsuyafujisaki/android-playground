package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

object LifecycleUtil {
    fun onStateChanged(fragment: Fragment) {
        fragment.lifecycle.addObserver(
            LifecycleEventObserver { source, event ->
                Log.d(source::class.java.simpleName, event.name)

                when (event) {
                    Lifecycle.Event.ON_CREATE -> {
                    }
                    Lifecycle.Event.ON_START -> {
                    }
                    Lifecycle.Event.ON_RESUME -> {
                    }
                    Lifecycle.Event.ON_PAUSE -> {
                    }
                    Lifecycle.Event.ON_STOP -> {
                    }
                    Lifecycle.Event.ON_DESTROY -> {
                    }
                    Lifecycle.Event.ON_ANY -> {
                    }
                }
            }
        )
    }
}
