package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp

private fun <T> T.applyIf(condition: Boolean, block: T.() -> T) = if (condition) block() else this

fun Modifier.circleBorder(
    isCircle: Boolean = false,
    border: Pair<Dp, Color>? = null
) = applyIf(isCircle) {
    clip(CircleShape)
}.applyIf(border != null) {
    border(border!!.first, border.second, if (isCircle) CircleShape else RectangleShape)
}

fun Modifier.circleBorder2(
    isCircle: Boolean = false,
    border: Pair<Dp, Color>? = null
): Modifier {
    val m = if (isCircle) clip(CircleShape) else this
    return if (border != null) {
        m.border(border.first, border.second, if (isCircle) CircleShape else RectangleShape)
    } else {
        m
    }
}
