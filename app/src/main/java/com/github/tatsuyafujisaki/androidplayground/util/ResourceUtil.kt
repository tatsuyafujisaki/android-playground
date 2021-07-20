package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import androidx.annotation.DimenRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

object ResourceUtil {
    /** Converts dp to pixel. */
    val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    /** Converts pixel to dp. */
    val Int.dp get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    @Px
    fun Resources.getPixel(@DimenRes id: Int) = getDimensionPixelSize(id)

    /**
     * Impractical redundant explanatory wrapper.
     * Use [Fragment.getString]
     */
    fun Resources.getString2(@StringRes id: Int) = getString(id)
}
