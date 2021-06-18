package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.View.OnKeyListener
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.findNavController

/**
 * Impractical redundant explanatory wrappers
 */
object ActivityUtil {
    fun Activity.canResolveActivity(intent: Intent) =
        packageManager.resolveActivity(intent, 0) != null

    fun Activity.getNavController(@IdRes navHostFragmentId: Int) =
        findNavController(navHostFragmentId)

    fun FragmentActivity.clearViewModels() {
        viewModelStore.clear()
    }

    fun ComponentActivity.hasEnabledCallbacks() =
        onBackPressedDispatcher.hasEnabledCallbacks()

    private fun Activity.hideKeyboard() {
        currentFocus?.windowToken?.let {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(it, 0)
        }
    }

    fun Activity.hideKeyboardOnEnter() = OnKeyListener { v, keyCode, _ ->
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            hideKeyboard()
            true
        } else {
            false
        }
    }

    fun Activity.getExtraString(key: String) = intent?.extras?.getString(key).orEmpty()

    fun ComponentActivity.logStateChanged(tag: String) {
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.d(tag, event.toString())
        })
    }

    fun Activity.hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        with(WindowInsetsControllerCompat(window, window.decorView)) {
            hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
        }
    }

    fun Activity.showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, window.decorView)
            .show(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
    }
}
