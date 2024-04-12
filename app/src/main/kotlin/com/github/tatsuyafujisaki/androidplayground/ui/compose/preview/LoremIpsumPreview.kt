package com.github.tatsuyafujisaki.androidplayground.ui.compose.preview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

class LoremIpsum2 : LoremIpsum(words = 2)
private class LoremIpsum8 : LoremIpsum(words = 8)
private class LoremIpsum18 : LoremIpsum(words = 18)

@Preview(showBackground = true)
@Composable
private fun LoremIpsumPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    Text(text = text)
}

@Preview(showBackground = true)
@Composable
private fun LoremIpsum8Preview(@PreviewParameter(LoremIpsum8::class) text: String) {
    Text(text = text)
}

@Preview(showBackground = true)
@Composable
private fun LoremIpsum18Preview(@PreviewParameter(LoremIpsum18::class) text: String) {
    Text(text = text)
}
