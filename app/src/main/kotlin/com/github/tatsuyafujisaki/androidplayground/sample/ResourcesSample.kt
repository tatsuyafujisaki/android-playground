package com.github.tatsuyafujisaki.androidplayground.sample

import android.content.res.Configuration
import android.content.res.Resources

object ResourcesSample {
    fun dpToPixel(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

    fun pixelToDp(pixel: Int) = (pixel / Resources.getSystem().displayMetrics.density).toInt()

    object OrientationSample {
        fun isPortrait(resources: Resources) =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        fun isLandscape(resources: Resources) =
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}
