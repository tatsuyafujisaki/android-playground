package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.R

@Preview
@Composable
fun BrokenImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Box(
        modifier =
        modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.broken_image_24px),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
        )
    }
}
