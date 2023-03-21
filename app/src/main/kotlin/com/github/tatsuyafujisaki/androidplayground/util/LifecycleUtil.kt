package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

object LifecycleUtil {
    private val observer1 = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {}
        override fun onStart(owner: LifecycleOwner) {}
        override fun onResume(owner: LifecycleOwner) {}
        override fun onPause(owner: LifecycleOwner) {}
        override fun onStop(owner: LifecycleOwner) {}
        override fun onDestroy(owner: LifecycleOwner) {}
    }

    private val observer2 = LifecycleEventObserver { source, event ->
        Log.d(source::class.java.simpleName, event.name)
        when (event) {
            Lifecycle.Event.ON_CREATE -> {}
            Lifecycle.Event.ON_START -> {}
            Lifecycle.Event.ON_RESUME -> {}
            Lifecycle.Event.ON_PAUSE -> {}
            Lifecycle.Event.ON_STOP -> {}
            Lifecycle.Event.ON_DESTROY -> {}
            Lifecycle.Event.ON_ANY -> {}
        }
    }

    fun onCreate(fragment: Fragment) {
        with(fragment.viewLifecycleOwner.lifecycle) {
            addObserver(observer1)
            addObserver(observer2)
        }
    }

    fun onDestroy(fragment: Fragment) {
        with(fragment.viewLifecycleOwner.lifecycle) {
            removeObserver(observer1)
            removeObserver(observer2)
        }
    }
}
