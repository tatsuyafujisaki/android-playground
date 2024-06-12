package com.github.tatsuyafujisaki.androidplayground.ui.compose.media

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.tatsuyafujisaki.androidplayground.R

@Preview
@Composable
private fun IconExample1() {
    Icon(
        imageVector = Icons.Default.Android,
        contentDescription = null,
        modifier = Modifier.size(size = 48.dp),
        tint = Color.Green,
    )
}

@Preview
@Composable
private fun IconExample2() {
    Icon(
        painter = painterResource(id = R.drawable.ic_android_robot),
        contentDescription = null,
        modifier = Modifier.size(size = 48.dp),
        tint = Color.Green,
    )
}
