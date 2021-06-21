package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updatePadding
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.getColor2
import com.github.tatsuyafujisaki.androidplayground.util.ContextUtil.getDrawable2

object ResourceUtil {
    /** Converts dp to pixel */
    val Int.toPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    /** Converts pixel to dp */
    val Int.toDp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    /**
     * Prefer [ContextUtil.getColor2] for simplicity.
     */
    fun Resources.getColor2(@ColorRes id: Int) = getColor(id, null)

    /**
     * Prefer [ContextUtil.getDrawable2] for simplicity.
     */
    fun Resources.getDrawable2(@DrawableRes id: Int) = ResourcesCompat.getDrawable(this, id, null)
    fun Resources.getPixel(@DimenRes id: Int) = getDimensionPixelSize(id)
    fun Resources.getString2(@StringRes id: Int) = getString(id)

    fun View.updatePaddingByRes(
        resources: Resources,
        @DimenRes leftId: Int? = null,
        @DimenRes topId: Int? = null,
        @DimenRes rightId: Int? = null,
        @DimenRes bottomId: Int? = null
    ) {
        updatePadding(
            leftId?.let(resources::getDimensionPixelSize) ?: paddingLeft,
            topId?.let(resources::getDimensionPixelSize) ?: paddingTop,
            rightId?.let(resources::getDimensionPixelSize) ?: paddingRight,
            bottomId?.let(resources::getDimensionPixelSize) ?: paddingBottom
        )
    }

    fun View.updatePaddingInDp(
        leftDp: Int? = null,
        topDp: Int? = null,
        rightDp: Int? = null,
        bottomDp: Int? = null
    ) {
        fun toPx2(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

        updatePadding(
            leftDp?.let(::toPx2) ?: paddingLeft,
            topDp?.let(::toPx2) ?: paddingTop,
            rightDp?.let(::toPx2) ?: paddingRight,
            bottomDp?.let(::toPx2) ?: paddingBottom
        )
    }
}
