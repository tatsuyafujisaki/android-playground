package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.getColor2
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.getDrawable2

/**
 * To get a color, use [ContextUtil.getColor2].
 * To get a drawable, use [ContextUtil.getDrawable2].
 */
object ResourceUtil {
    fun Resources.getPixel(@DimenRes id: Int) = getDimensionPixelOffset(id)
    fun Resources.getString2(@StringRes id: Int) = getString(id)
}
