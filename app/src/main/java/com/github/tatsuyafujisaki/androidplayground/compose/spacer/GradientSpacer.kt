package com.github.tatsuyafujisaki.androidplayground.compose.spacer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun GradientSpacerPreview() {
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(Brush.verticalGradient(listOf(Color.Red, Color.Transparent)))
    )
}
