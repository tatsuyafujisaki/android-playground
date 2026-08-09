package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

@Preview(showBackground = true)
@Composable
private fun IconButtonExample() {
    val uriHandler = LocalUriHandler.current

    IconButton(onClick = { uriHandler.openUri("https://example.com") }) {
        Icon(
            painter = painterResource(R.drawable.favorite_24),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}
