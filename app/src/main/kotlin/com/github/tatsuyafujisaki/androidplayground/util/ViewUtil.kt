package com.github.tatsuyafujisaki.androidplayground.util

import android.content.res.Resources
import android.view.MotionEvent
import android.view.View
import androidx.annotation.DimenRes
import androidx.core.view.updatePadding

@Suppress("unused")
object ViewUtil {
    private const val TAG = "ViewUtil"

    fun updatePaddingByRes(
        view: View,
        resources: Resources,
        @DimenRes leftId: Int? = null,
        @DimenRes topId: Int? = null,
        @DimenRes rightId: Int? = null,
        @DimenRes bottomId: Int? = null,
    ) {
        view.updatePadding(
            leftId?.let(resources::getDimensionPixelSize) ?: view.paddingLeft,
            topId?.let(resources::getDimensionPixelSize) ?: view.paddingTop,
            rightId?.let(resources::getDimensionPixelSize) ?: view.paddingRight,
            bottomId?.let(resources::getDimensionPixelSize) ?: view.paddingBottom,
        )
    }

    fun updatePaddingInDp(
        view: View,
        leftDp: Int? = null,
        topDp: Int? = null,
        rightDp: Int? = null,
        bottomDp: Int? = null,
    ) {
        fun toPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

        view.updatePadding(
            leftDp?.let(::toPx) ?: view.paddingLeft,
            topDp?.let(::toPx) ?: view.paddingTop,
            rightDp?.let(::toPx) ?: view.paddingRight,
            bottomDp?.let(::toPx) ?: view.paddingBottom,
        )
    }

    fun View.setOnDownUpListener(
        onActionDown: () -> Unit,
        onActionUp: () -> Unit,
    ) {
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> onActionDown()
                MotionEvent.ACTION_UP -> {
                    onActionUp()
                    // performClick() calls a callback registered with setOnClickListener().
                    // Without performClick(), a callback registered with setOnClickListener() will never be called
                    // because setOnTouchListener() consumes clicking.
                    v.performClick()
                }
            }
            true
        }
    }

    fun setOnSafeClickListener(
        view: View,
        delayMillis: Long = 1_000,
        onClick: () -> Unit,
    ) {
        view.setOnClickListener {
            view.isClickable = false
            view.postDelayed({
                view.isClickable = true
            }, delayMillis)
            onClick()
        }
    }
}
