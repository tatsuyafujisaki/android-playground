package com.github.tatsuyafujisaki.androidplayground.ui.compose.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.github.tatsuyafujisaki.androidplayground.ui.compose.preview.LoremIpsum2

@Preview(showBackground = true)
@Composable
fun HorizontallyCenteredTextExample(@PreviewParameter(LoremIpsum2::class) text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        maxLines = 1,
    )
}
