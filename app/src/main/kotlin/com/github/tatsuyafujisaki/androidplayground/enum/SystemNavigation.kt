package com.github.tatsuyafujisaki.androidplayground.enum

import android.content.Context
import android.provider.Settings

enum class SystemNavigation {
    THREE_BUTTON,
    TWO_BUTTON,
    GESTURE;

    companion object {
        fun from(context: Context): SystemNavigation? {
            val mode = Settings.Secure.getInt(context.contentResolver, "navigation_mode", -1)
            return values().getOrNull(mode)
        }
    }
}
