package com.github.tatsuyafujisaki.androidplayground.ui.compose.media.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun IconToggleButtonExample() {
    var checked by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
    ) {
        Icon(
            imageVector = if (checked) Icons.Default.Favorite else Icons.Outlined.HeartBroken,
            contentDescription = null,
            tint = Color.Red,
        )
    }
}
