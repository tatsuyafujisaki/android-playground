package com.github.tatsuyafujisaki.androidplayground.ui.compose.navigation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PopOrFinishExample(
    activity: Activity = LocalActivity.current as Activity,
    navController: NavController = rememberNavController(),
) {
    if (!navController.popBackStack()) {
        activity.finish()
    }
}
