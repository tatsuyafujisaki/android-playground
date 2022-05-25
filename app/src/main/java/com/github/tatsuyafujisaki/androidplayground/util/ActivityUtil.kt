package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.core.view.ViewCompat
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

        fun openKeyboard(activity: Activity) {
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
        fun fullscreen(activity: Activity) {
            ViewCompat.getWindowInsetsController(activity.window.decorView)?.run {
                // Prevent only touching from showing the system bars.
                systemBarsBehavior = indowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(Type.systemBars())
            }
        }

        fun exitFullscreen(activity: Activity) {
            ViewCompat.getWindowInsetsController(activity.window.decorView)?.show(Type.systemBars())
        }
    }

    fun canResolveActivity(activity: Activity, intent: Intent) =
        activity.packageManager.resolveActivity(intent, 0) != null

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
