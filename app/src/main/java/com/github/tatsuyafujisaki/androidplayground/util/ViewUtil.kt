package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import android.view.View
import androidx.annotation.DimenRes
import androidx.core.view.updatePadding

object ViewUtil {
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
        fun toPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

        updatePadding(
            leftDp?.let(::toPx) ?: paddingLeft,
            topDp?.let(::toPx) ?: paddingTop,
            rightDp?.let(::toPx) ?: paddingRight,
            bottomDp?.let(::toPx) ?: paddingBottom
        )
    }

    fun View.setOnSafeClickListener(delayMillis: Long = 1000L, onClick: (View) -> Unit) {
        setOnClickListener {
            isClickable = false
            postDelayed({
                isClickable = true
            }, delayMillis)
            onClick(it)
        }
    }
}
