package com.github.tatsuyafujisaki.androidplayground.compose.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PopOrFinishExample(
    activity: Activity = LocalContext.current as Activity,
    navController: NavController = rememberNavController()
) {
    if (!navController.popBackStack()) {
        activity.finish()
    }
}
