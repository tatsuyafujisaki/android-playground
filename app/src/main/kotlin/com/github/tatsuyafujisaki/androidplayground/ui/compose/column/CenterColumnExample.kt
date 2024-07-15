package com.github.tatsuyafujisaki.androidplayground.ui.compose.column

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CenterColumnExample(
    content: @Composable ColumnScope.() -> Unit,
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = onClick != null, onClick = onClick ?: {}),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content,
    )
}

@Preview(showBackground = true)
@Composable
private fun CenterColumnExamplePreview() {
    CenterColumnExample(
        content = {
            Text(text = "🍎")
            Text(text = "🍏")
            Text(text = "🍊")
        },
    )
}
