package com.github.tatsuyafujisaki.androidplayground.ui.compose.spacer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Suppress("unused")
@Composable
private fun SpacerHeight(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    height: Dp = 100.dp,
) {
    Spacer(
        modifier = modifier
            .background(color = color)
            .fillMaxWidth()
            .height(height = height),
    )
}

@Composable
private fun SpacerWidth(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    width: Dp = 100.dp,
) {
    Spacer(
        modifier = modifier
            .background(color = color)
            .width(width = width)
            .fillMaxHeight(),
    )
}

@Composable
private fun SpacerWidthHeight(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    width: Dp = 100.dp,
    height: Dp = 100.dp,
) {
    Spacer(
        modifier = modifier
            .background(color = color)
            .width(width = width)
            .height(height = height),
    )
}
