package com.github.tatsuyafujisaki.androidplayground.util

import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity

/**
 * Impractical redundant explanatory wrappers
 */
object ActivityUtil {
    fun FragmentActivity.clearViewModels() {
        viewModelStore.clear()
    }

    fun ComponentActivity.hasEnabledCallbacks() =
        onBackPressedDispatcher.hasEnabledCallbacks()
}
