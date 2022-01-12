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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CenterBoxExample(
    height: Dp = 100.dp,
    background: Color = Color.White,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(background)
                .clickable(enabled = onClick != null, onClick = onClick ?: {}),
            contentAlignment = Alignment.Center,
            content = content
        )
        Divider(color = Color.Red)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CenterBoxExample {
        Text("Sample")
    }
}
