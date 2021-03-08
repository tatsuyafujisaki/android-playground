package com.github.tatsuyafujisaki.androidplayground.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

object LifecycleUtil {
    fun Lifecycle.onStateChanged() {
        addObserver(LifecycleEventObserver { source, event ->
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
        })
    }

    @Deprecated("Use onStateChanged()")
    fun Lifecycle.onStateChangedDeprecated() {
        addObserver(
            object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
                fun onCreate() {
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                fun onStart() {
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
                fun onResume() {
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
                fun onPause() {
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                fun onStop() {
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
                fun onAny() {
                }
            }
        )
    }
}
