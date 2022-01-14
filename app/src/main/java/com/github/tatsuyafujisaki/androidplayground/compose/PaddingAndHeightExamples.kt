package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PaddingAndHeightExample1() {
    Box(
        // Total size including padding is 150x150.
        modifier = Modifier
            .padding(50.dp)
            .size(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text("Sample")
    }
}

@Preview(showBackground = true)
@Composable
fun PaddingAndHeightExample2() {
    Box(
        // Total size including padding is 100x100.
        modifier = Modifier
            .size(100.dp)
            .padding(50.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text("Sample")
    }
}
