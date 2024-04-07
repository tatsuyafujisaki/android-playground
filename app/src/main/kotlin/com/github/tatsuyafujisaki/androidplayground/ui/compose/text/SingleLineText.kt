package com.github.tatsuyafujisaki.androidplayground.ui.compose.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

@Preview(showBackground = true)
@Composable
fun SingleLineTextPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}
