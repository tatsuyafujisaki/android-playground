package com.github.tatsuyafujisaki.androidplayground.ui.compose.navigation

import android.app.Activity
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

/**
 * Pops the top entry off the back stack, or finishes the activity if only the root entry remains.
 *
 * Call this function from an event handler such as `onClick`, not during composition.
 */
fun popOrFinish(
    activity: Activity,
    backStack: NavBackStack<NavKey>,
) {
    if (backStack.size > 1) {
        backStack.removeLastOrNull()
    } else {
        activity.finish()
    }
}
