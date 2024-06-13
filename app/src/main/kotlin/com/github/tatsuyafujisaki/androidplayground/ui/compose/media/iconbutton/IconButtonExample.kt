package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.NullableLambdaProvider

@Composable
private fun IconButtonExample(onClick: (() -> Unit)?) {
    IconButton(
        onClick = onClick ?: {},
        modifier = Modifier.minimumInteractiveComponentSize(),
        enabled = onClick != null,
    ) {
        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
            tint = Color.Green,
        )
    }
}

@Preview
@Composable
private fun IconButtonExamplePreview(
    @PreviewParameter(NullableLambdaProvider::class) onClick: (() -> Unit)?
) {
    IconButtonExample(onClick = onClick)
}
