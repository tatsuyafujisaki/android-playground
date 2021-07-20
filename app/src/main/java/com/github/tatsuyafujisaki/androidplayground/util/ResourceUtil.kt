package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment

object ResourceUtil {
    /** Converts dp to pixel. */
    val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    /** Converts pixel to dp. */
    val Int.dp get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    /**
     * How to get the number of pixels from [DimenRes]:
     * [Resources.getDimensionPixelSize]
     */

    /**
     * How to get a string from a resource:
     * [Fragment.getString] (Simpler than [Resources.getString])
     */
}
