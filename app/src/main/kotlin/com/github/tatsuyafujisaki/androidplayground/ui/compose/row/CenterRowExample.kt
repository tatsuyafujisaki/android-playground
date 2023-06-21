package com.github.tatsuyafujisaki.androidplayground.ui.compose.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CenterRowExample(
    background: Color = Color.White,
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .clickable(enabled = onClick != null, onClick = onClick ?: {}),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun CenterRowExamplePreview() {
    CenterRowExample {
        Text("Bacon")
        Text("Lettuce")
        Text("Tomato")
    }
}
