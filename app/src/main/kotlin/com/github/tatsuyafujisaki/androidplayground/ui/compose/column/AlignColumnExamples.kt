package com.github.tatsuyafujisaki.androidplayground.ui.compose.column

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun AlignColumnExample1(onClick: (() -> Unit)? = null) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "🍎", modifier = Modifier.align(alignment = Alignment.Start))
        Text(text = "🍏", modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
        Text(text = "🍊", modifier = Modifier.align(alignment = Alignment.End))
    }
}

@Preview(showBackground = true)
@Composable
fun AlignColumnExample2(onClick: (() -> Unit)? = null) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "🍎", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
        Text(text = "🍏", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Text(text = "🍊", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
    }
}
