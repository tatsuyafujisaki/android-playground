package com.github.tatsuyafujisaki.androidplayground.ui.compose.navigation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack

@Composable
fun PopOrFinishExample(
    activity: Activity = LocalActivity.current as Activity,
    backStack: NavBackStack<NavKey> = rememberNavBackStack(),
) {
    if (backStack.size > 1) {
        backStack.removeLastOrNull()
    } else {
        activity.finish()
    }
}
