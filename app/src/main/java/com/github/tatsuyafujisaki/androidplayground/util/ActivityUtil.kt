package com.github.tatsuyafujisaki.androidplayground.util

import androidx.fragment.app.FragmentActivity

object ActivityUtil {
    /**
     * Redundant explanatory wrapper. Unnecessary in practice.
     */
    fun clearViewModels(activity: FragmentActivity) {
        activity.viewModelStore.clear()
    }
}
