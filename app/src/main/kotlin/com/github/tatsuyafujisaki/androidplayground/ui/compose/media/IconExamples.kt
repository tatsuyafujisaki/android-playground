package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

@Preview
@Composable
private fun IconExample1() {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        tint = Color.Red,
    )
}

@Preview
@Composable
private fun IconExample2() {
    Icon(
        painter = painterResource(id = R.drawable.ic_android_robot),
        contentDescription = null,
        tint = Color.Green,
    )
}
