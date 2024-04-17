package com.github.tatsuyafujisaki.androidplayground.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun MyDivider() {
    HorizontalDivider(thickness = 10.dp, color = Yellow)
}

@Preview
@Composable
fun BackgroundPaddingSizeExample() {
    val size = 40.dp
    val padding = 20.dp

    Column {
        Spacer(
            Modifier
                .background(Red)
                .padding(padding)
                .size(size)
        )
        MyDivider()
        Spacer(
            Modifier
                .background(Red)
                .size(size)
                .padding(padding)
        )
        MyDivider()
        Spacer(
            Modifier
                .padding(padding)
                .background(Red)
                .size(size)
        )
        MyDivider()
        Spacer(
            Modifier
                .padding(padding)
                .size(size)
                .background(Red)
        )
        MyDivider()
        Spacer(
            Modifier
                .size(size)
                .background(Red)
                .padding(padding)
        )
        MyDivider()
        Spacer(
            Modifier
                .size(size)
                .padding(padding)
                .background(Red)
        )
        MyDivider()
    }
}
