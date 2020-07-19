package com.github.tatsuyafujisaki.androidplayground.util

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding

/*
 * Simplified from:
 * https://github.com/android/animation-samples/blob/master/DrawableAnimations/app/src/main/java/com/example/android/drawableanimations/ViewBindingDelegates.kt
 * https://github.com/android/user-interface-samples/blob/master/People/app/src/main/java/com/example/android/people/ui/ViewBindingDelegates.kt
 * https://github.com/googlecodelabs/android-people/blob/master/app/src/main/java/com/example/android/people/ui/ViewBindingDelegates.kt
 */

/**
 * Usage: private val binding by viewBindings(MainActivityBinding::bind)
 */
inline fun <reified T : ViewBinding> FragmentActivity.viewBindings(
    crossinline bind: (View) -> T
) = object : Lazy<T> {

    private var cached: T? = null

    override val value: T
        get() = cached ?: bind(
            findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
        ).also {
            cached = it
        }

    override fun isInitialized() = cached != null
}

/**
 * Usage: private val binding by viewBindings(MainFragmentBinding::bind)
 */
inline fun <reified T : ViewBinding> Fragment.viewBindings(
    crossinline bind: (View) -> T
) = object : Lazy<T> {

    private var cached: T? = null

    override val value: T
        get() = cached ?: bind(requireView()).also {
            viewLifecycleOwner.lifecycle.addObserver(
                LifecycleEventObserver { _, event ->
                    /*
                     * ON_DESTROY here is called just before onDestroyView(), not onDestroy(),
                     * because the lifecycle is about Fragment's view, not Fragment itself.
                     */
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        cached = null
                    }
                }
            )
            cached = it
        }

    override fun isInitialized() = cached != null
}
