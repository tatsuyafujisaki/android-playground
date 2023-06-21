package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RoundedCornerExample(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
            .background(Color.Red)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.Blue)
    )
}
