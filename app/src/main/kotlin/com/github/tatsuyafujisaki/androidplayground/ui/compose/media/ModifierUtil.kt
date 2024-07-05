package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp

fun Modifier.circleBorder(
    isCircle: Boolean = false,
    border: Pair<Dp, Color>? = null,
): Modifier {
    val m = if (isCircle) clip(CircleShape) else this
    return if (border != null) {
        m.border(border.first, border.second, if (isCircle) CircleShape else RectangleShape)
    } else {
        m
    }
}
