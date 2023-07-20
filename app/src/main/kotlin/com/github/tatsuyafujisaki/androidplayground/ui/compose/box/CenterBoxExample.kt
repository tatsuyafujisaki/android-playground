package com.github.tatsuyafujisaki.androidplayground.ui.compose.box

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
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
    content: @Composable BoxScope.() -> Unit,
) {
        Box(
            modifier = Modifier
                .sizeIn(minWidth = 48.dp, minHeight = 48.dp)
                .fillMaxWidth()
                .height(height)
                .background(background)
                .clickable(enabled = onClick != null, onClick = onClick ?: {}),
            contentAlignment = Alignment.Center,
            content = content
        )
}

@Preview(showBackground = true)
@Composable
private fun CenterBoxExamplePreview() {
    CenterBoxExample {
        Text("Sample")
    }
}
