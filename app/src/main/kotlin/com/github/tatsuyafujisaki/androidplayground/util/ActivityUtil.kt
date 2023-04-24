package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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
        fun getInputMethodManager(activity: Activity) =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        fun showKeyboard(activity: Activity) {
            WindowInsetsControllerCompat(
                activity.window,
                activity.window.decorView
            ).show(Type.ime())
        }

        private fun hideKeyboard(activity: Activity) {
            WindowInsetsControllerCompat(
                activity.window,
                activity.window.decorView
            ).hide(Type.ime())
        }

        fun hideKeyboardOldWay(activity: Activity) {
            (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
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

    object Fullscreen {
        /**
         * <a href="https://developer.android.com/develop/ui/views/layout/immersive">Hide system bars for immersive mode</a>
         */
        fun fullscreen(window: Window) {
            with(WindowCompat.getInsetsController(window, window.decorView)) {
                // Prevent only touching from showing the system bars.
                systemBarsBehavior = WindowInsetsControllerCompat
                    .BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(Type.systemBars())
            }
        }

        fun exitFullscreen(window: Window) {
            WindowCompat.getInsetsController(window, window.decorView).show(Type.systemBars())
        }
    }

    object ScreenAwake {
        fun keepScreenOn(activity: Activity) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }

        fun stopKeepingScreenOn(activity: Activity) {
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
        activity.lifecycle.addObserver(
            LifecycleEventObserver { _, event ->
                Log.d(tag, event.toString())
            }
        )
    }
}
