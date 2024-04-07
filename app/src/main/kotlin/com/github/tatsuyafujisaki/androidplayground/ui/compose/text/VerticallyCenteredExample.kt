package com.github.tatsuyafujisaki.androidplayground.ui.compose.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons.AutoMirrored
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

@Preview(showBackground = true)
@Composable
fun VerticallyCenteredText1Example(@PreviewParameter(LoremIpsum::class) text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            // centers the text vertically.
            modifier = Modifier.align(Alignment.CenterVertically),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Icon(
            imageVector = AutoMirrored.Default.NavigateNext,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerticallyCenteredText2Example(@PreviewParameter(LoremIpsum::class) text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        // centers children vertically.
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            modifier = Modifier,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Icon(
            imageVector = AutoMirrored.Default.NavigateNext,
            contentDescription = null,
        )
    }
}
