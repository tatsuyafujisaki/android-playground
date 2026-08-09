package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.R

@Preview(showBackground = true)
@Composable
private fun ShadowIconExample(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val painter = painterResource(R.drawable.favorite_24)
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .offset(y = 1.dp)
                .blur(radius = 1.dp),
            tint = Color.Black,
        )
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.Green,
        )
    }
}
