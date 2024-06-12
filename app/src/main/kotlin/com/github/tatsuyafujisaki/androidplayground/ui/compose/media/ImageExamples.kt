package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.R

@Preview(showBackground = true)
@Composable
private fun ImageExample1() {
    Image(
        imageVector = Icons.Default.Android,
        contentDescription = null,
        modifier = Modifier.size(size = 48.dp),
        colorFilter = ColorFilter.tint(color = Color.Green)
    )
}

@Preview
@Composable
private fun IconExample2() {
    Image(
        painter = painterResource(id = R.drawable.ic_android_robot),
        contentDescription = null,
        modifier = Modifier.size(size = 48.dp),
        colorFilter = ColorFilter.tint(color = Color.Green)
    )
}
