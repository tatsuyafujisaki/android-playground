package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.view.OrientationEventListener

object ResourcesUtil {
    fun dpToPixel(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

    fun pixelToDp(pixel: Int) = (pixel / Resources.getSystem().displayMetrics.density).toInt()

    /**
     * Read a text file in the "resources" directory.
     * cf. [ContextUtil.readAssetAsText]
     */
    fun readResourceAsText(name: String) = object {}.javaClass.classLoader!!.getResource(name).readText()

    object OrientationUtil {
        fun isPortrait(resources: Resources) = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        fun isLandscape(resources: Resources) = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        fun enableOrientationEventListener(
            context: Context,
            onOrientationChanged: (Int) -> Unit,
        ) {
            object : OrientationEventListener(context) {
                override fun onOrientationChanged(orientation: Int) {
                    onOrientationChanged(orientation)
                }
            }.enable()
        }
    }
}
