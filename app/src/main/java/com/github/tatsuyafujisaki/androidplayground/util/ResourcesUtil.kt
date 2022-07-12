package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Configuration
import android.content.res.Resources

object ResourcesUtil {
    fun dpToPixel(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()
    fun pixelToDp(pixel: Int) = (pixel / Resources.getSystem().displayMetrics.density).toInt()

    object OrientationUtil {
        fun isPortrait(resources: Resources) =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        fun isLandscape(resources: Resources) =
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}
