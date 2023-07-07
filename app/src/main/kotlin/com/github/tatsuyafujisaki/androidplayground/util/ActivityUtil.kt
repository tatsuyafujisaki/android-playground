package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

/**
 * Impractical redundant explanatory wrappers
 */
object ActivityUtil {
    object Keyboard {
        fun showKeyboard(activity: Activity) {
            WindowInsetsControllerCompat(
                activity.window, activity.window.decorView
            ).show(Type.ime())
        }

        private fun hideKeyboard(activity: Activity) {
            WindowInsetsControllerCompat(
                activity.window, activity.window.decorView
            ).hide(Type.ime())
        }

        fun hideKeyboardOnEnter(activity: Activity) {
            View.OnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideKeyboard(activity)
                    true
                } else {
                    false
                }
            }
        }
    }

    object SystemBars {
        fun toggleSystemBars(window: Window, isVisible: Boolean) {
            if (isVisible) showSystemBars(window) else hideSystemBars(window)
        }

        private fun showSystemBars(window: Window) {
            WindowCompat.getInsetsController(window, window.decorView).show(Type.systemBars())
        }

        // https://developer.android.com/develop/ui/views/layout/immersive
        private fun hideSystemBars(window: Window) {
            with(WindowCompat.getInsetsController(window, window.decorView)) {
                systemBarsBehavior = WindowInsetsControllerCompat
                    .BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(Type.systemBars())
            }
        }
    }

    object OssLicenses {
        fun startOssLicensesMenuActivity(context: Context, title: String = "") {
            if (title.isNotBlank()) OssLicensesMenuActivity.setActivityTitle(title)
            context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
    }

    fun keepScreenOn(activity: Activity, on: Boolean) {
        val window = activity.window
        if (on) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    fun getNavController(activity: Activity, @IdRes navHostFragmentId: Int) =
        activity.findNavController(navHostFragmentId)

    fun hasEnabledCallbacks(activity: ComponentActivity) {
        activity.onBackPressedDispatcher.hasEnabledCallbacks()
    }

    fun getExtraString(activity: Activity, key: String) =
        activity.intent?.extras?.getString(key).orEmpty()
}
