package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.compose.preview.BooleanProvider
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun ComposablePlayground(visible: Boolean) {
    Text(
        text = "Content to display after content has loaded",
        modifier = Modifier
            .padding(16.dp)
            .placeholder(visible = visible)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewComposablePlayground(
    @PreviewParameter(BooleanProvider::class) visible: Boolean
) {
    ComposablePlayground(visible)
}
