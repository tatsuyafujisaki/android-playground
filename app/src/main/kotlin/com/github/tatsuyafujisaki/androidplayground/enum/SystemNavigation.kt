package com.github.tatsuyafujisaki.androidplayground.enum

import android.annotation.SuppressLint
import android.content.res.Resources

enum class SystemNavigation {
    THREE_BUTTON,
    TWO_BUTTON,
    GESTURE,
    UNKNOWN;

    companion object {
        @SuppressLint("DiscouragedApi")
        fun from(resources: Resources): SystemNavigation {
            val resourceId =
                resources.getIdentifier("config_navBarInteractionMode", "integer", "android")
            val mode = runCatching { resources.getInteger(resourceId) }.getOrDefault(-1)
            return values().getOrElse(mode) { UNKNOWN }
        }
    }
}
