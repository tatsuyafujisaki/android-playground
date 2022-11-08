package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Configuration
import android.content.res.Resources

object ResourcesUtil {
    fun dpToPixel(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()
    fun pixelToDp(pixel: Int) = (pixel / Resources.getSystem().displayMetrics.density).toInt()

    /**
     * Read a text file in the "resources" directory.
     * cf. [ContextUtil.readAssetAsText]
     */
    fun readResourceAsText(name: String) =
        object {}.javaClass.classLoader?.getResource(name)?.readText().orEmpty()

    object OrientationUtil {
        fun isPortrait(resources: Resources) =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        fun isLandscape(resources: Resources) =
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}
