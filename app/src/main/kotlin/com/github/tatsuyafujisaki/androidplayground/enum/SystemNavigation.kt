package com.github.tatsuyafujisaki.androidplayground.enum

import android.content.Context
import android.provider.Settings

enum class SystemNavigation {
    THREE_BUTTON,
    TWO_BUTTON,
    GESTURE;

    companion object {
        fun create(context: Context) = values().getOrNull(
            Settings.Secure.getInt(context.contentResolver, "navigation_mode", -1)
        )
    }
}
