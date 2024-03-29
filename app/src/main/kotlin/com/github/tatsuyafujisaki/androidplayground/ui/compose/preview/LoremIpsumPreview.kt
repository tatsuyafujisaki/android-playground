package com.github.tatsuyafujisaki.androidplayground.ui.compose.preview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

private class LoremIpsum18 : LoremIpsum(words = 18)

@Preview
@Composable
private fun LoremIpsumPreview(@PreviewParameter(LoremIpsum18::class) text: String) {
    Text(text)
}
