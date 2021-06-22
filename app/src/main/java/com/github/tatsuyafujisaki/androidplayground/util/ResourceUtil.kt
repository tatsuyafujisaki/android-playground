package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.getColor2
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.getDrawable2

object ResourceUtil {
    /** Converts dp to pixel */
    val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    /** Converts pixel to dp */
    val Int.dp get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    /**
     * Prefer [ContextUtil.getColor2] for simplicity.
     */
    @ColorInt
    fun Resources.getColor2(@ColorRes id: Int) = getColor(id, null)

    /**
     * Prefer [ContextUtil.getDrawable2] for simplicity.
     */
    fun Resources.getDrawable2(@DrawableRes id: Int) = ResourcesCompat.getDrawable(this, id, null)

    @Px
    fun Resources.getPixel(@DimenRes id: Int) = getDimensionPixelSize(id)
    fun Resources.getString2(@StringRes id: Int) = getString(id)
}
