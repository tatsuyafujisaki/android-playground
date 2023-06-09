package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.findNavController

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

    fun keepScreenOn(activity: Activity, on: Boolean) {
        if (on) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    fun getNavController(activity: Activity, @IdRes navHostFragmentId: Int) =
        activity.findNavController(navHostFragmentId)

    fun hasEnabledCallbacks(activity: ComponentActivity) {
        activity.onBackPressedDispatcher.hasEnabledCallbacks()
    }

    fun getExtraString(activity: Activity, key: String) =
        activity.intent?.extras?.getString(key).orEmpty()

    fun logStateChanged(activity: ComponentActivity, tag: String) {
        activity.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(tag, event.toString())
        })
    }
}
