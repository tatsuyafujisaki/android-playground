package com.github.tatsuyafujisaki.androidplayground.util

import androidx.fragment.app.FragmentActivity

/**
 * Impractical redundant explanatory wrappers
 */
object ActivityUtil {
    fun clearViewModels(activity: FragmentActivity) {
        activity.viewModelStore.clear()
    }
}
