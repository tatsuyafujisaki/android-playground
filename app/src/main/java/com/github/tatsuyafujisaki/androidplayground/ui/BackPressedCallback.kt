package com.github.tatsuyafujisaki.androidplayground.ui

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

class BackPressedCallback(fragment: Fragment, callback: () -> Unit) {
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            callback()
        }
    }

    init {
        fragment.lifecycle.addObserver(
            LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> fragment
                        .requireActivity()
                        .onBackPressedDispatcher
                        .addCallback(fragment.viewLifecycleOwner, onBackPressedCallback)
                    Lifecycle.Event.ON_STOP -> onBackPressedCallback.remove()
                    else -> {}
                }
            }
        )
    }
}
