package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CenterBoxExample(
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = onClick != null, onClick = onClick ?: {}),
            contentAlignment = Alignment.Center,
            content = content
        )
        Divider(color = Color.Red)
    }
}

@Preview(showBackground = true)
@Composable
private fun CenterBoxExamplePreview() {
    CenterBoxExample {
        Text("Sample")
    }
}
