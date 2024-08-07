package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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

@Composable
fun Modifier.fillScreenWidth() = width(width = LocalConfiguration.current.screenWidthDp.dp)

@Composable
fun Modifier.fillScreenHeight() = height(height = LocalConfiguration.current.screenHeightDp.dp)

@Composable
fun Modifier.logSizeChanged() = onSizeChanged {
    Log.d("👀", "onSizeChanged > size: $it[dp]")
    Log.d("👀", "onSizeChanged > width: ${it.width.dp}")
    Log.d("👀", "onSizeChanged > height: ${it.height.dp}")
}
