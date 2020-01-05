package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import androidx.annotation.IdRes
import androidx.navigation.findNavController

object ActivityUtils {
    /**
     * This is a redundant wrapper as a cheat sheet. Don't use it in practice.
     *
     * @id Resource ID of FragmentContainerView where NavHostFragment is set.
     */
    fun getNavController(activity: Activity, @IdRes id: Int) = activity.findNavController(id)
}
