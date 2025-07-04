package com.github.tatsuyafujisaki.androidplayground.enums

import android.content.Context
import android.provider.Settings

enum class SystemNavigation {
    THREE_BUTTON,
    TWO_BUTTON,
    GESTURE,
    ;

    companion object {
        fun create(context: Context) =
            entries.getOrNull(
                Settings.Secure.getInt(context.contentResolver, "navigation_mode", -1),
            )
    }
}
