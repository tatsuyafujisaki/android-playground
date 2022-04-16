package com.github.tatsuyafujisaki.androidplayground.util

import androidx.fragment.app.FragmentActivity

object ViewModelUtil {
    fun clearViewModels(activity: FragmentActivity) {
        activity.viewModelStore.clear()
    }
}
