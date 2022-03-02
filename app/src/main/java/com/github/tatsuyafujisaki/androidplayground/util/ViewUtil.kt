package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import android.view.View
import androidx.annotation.DimenRes
import androidx.core.view.updatePadding

object ViewUtil {
    fun updatePaddingByRes(
        view: View,
        resources: Resources,
        @DimenRes leftId: Int? = null,
        @DimenRes topId: Int? = null,
        @DimenRes rightId: Int? = null,
        @DimenRes bottomId: Int? = null
    ) {
        view.updatePadding(
            leftId?.let(resources::getDimensionPixelSize) ?: view.paddingLeft,
            topId?.let(resources::getDimensionPixelSize) ?: view.paddingTop,
            rightId?.let(resources::getDimensionPixelSize) ?: view.paddingRight,
            bottomId?.let(resources::getDimensionPixelSize) ?: view.paddingBottom
        )
    }

    fun updatePaddingInDp(
        view: View,
        leftDp: Int? = null,
        topDp: Int? = null,
        rightDp: Int? = null,
        bottomDp: Int? = null
    ) {
        fun toPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

        view.updatePadding(
            leftDp?.let(::toPx) ?: view.paddingLeft,
            topDp?.let(::toPx) ?: view.paddingTop,
            rightDp?.let(::toPx) ?: view.paddingRight,
            bottomDp?.let(::toPx) ?: view.paddingBottom
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
