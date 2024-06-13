package com.github.tatsuyafujisaki.androidplayground.ui.compose.button

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
private fun TextButton48(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .minimumInteractiveComponentSize()
            .clickable(onClick = onClick),
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun TextButton48Preview() {
    TextButton48(
        onClick = {},
        text = "あ"
    )
}
