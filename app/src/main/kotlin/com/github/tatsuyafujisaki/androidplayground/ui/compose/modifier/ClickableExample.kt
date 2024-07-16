package com.github.tatsuyafujisaki.androidplayground.ui.compose.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.NullableLambdaProvider

@Preview(showBackground = true)
@Composable
fun ClickableExample(
    @PreviewParameter(NullableLambdaProvider::class) onClick: (() -> Unit)?,
) {
    Spacer(
        modifier = Modifier.clickable(enabled = onClick != null, onClick = onClick ?: {}),
    )
}
