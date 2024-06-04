package com.github.tatsuyafujisaki.androidplayground.ui.compose.spacer.shape

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun CircleSpacerExample() {
    Spacer(
        modifier = Modifier
            .clip(shape = CircleShape) // avoids painting outside the corners.
            .background(color = Color.Magenta)
            .border(
                width = 4.dp,
                color = Color.Cyan,
                shape = CircleShape, // changes the shape of the border from a rectangle to a circle.
            )
            .size(size = 100.dp),
    )
}
