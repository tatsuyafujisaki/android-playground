package com.github.tatsuyafujisaki.androidplayground.ui.compose.spacer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun GradientSpacerExample() {
    Spacer(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Green),
                ),
            )
            .fillMaxSize()
    )
}
