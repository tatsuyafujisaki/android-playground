package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Usage in fragment:
 * viewLifecycleOwner.lifecycle.addObserver(SampleLifecycleObserver(requireContext()) {
 *   // Do something
 * })
 */
class SampleLifecycleObserver(
    private val context: Context,
    private val callback: () -> Unit
) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun registerSomething() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unregisterSomething() {
    }
}