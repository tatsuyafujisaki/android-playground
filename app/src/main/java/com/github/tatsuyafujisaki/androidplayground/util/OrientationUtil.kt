package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.res.Configuration

object OrientationUtil {
    fun isPortrait(context: Context) =
        context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    fun isLandscape(context: Context) =
        context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}
