package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Context
import android.view.KeyEvent
import android.view.View.OnKeyListener
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController

/**
 * Impractical redundant explanatory wrappers
 */
object ActivityUtil {
    val Activity.inputMethodManager
        get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    fun Activity.getNavController(@IdRes navHostFragmentId: Int) =
        findNavController(navHostFragmentId)

    fun FragmentActivity.clearViewModels() {
        viewModelStore.clear()
    }

    fun ComponentActivity.hasEnabledCallbacks() =
        onBackPressedDispatcher.hasEnabledCallbacks()

    fun Activity.hideKeyboardOnEnter() = OnKeyListener { v, keyCode, _ ->
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
            true
        } else {
            false
        }
    }
}
