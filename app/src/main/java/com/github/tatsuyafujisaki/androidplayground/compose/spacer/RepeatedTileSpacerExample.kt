package com.github.tatsuyafujisaki.androidplayground.compose.spacer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.tatsuyafujisaki.androidplayground.R

/**
 * @see [Stack Overflow](https://stackoverflow.com/a/73821217/10867055)
 */
@Preview
@Composable
private fun RepeatedTileSpacerExample() {
    val imageBitmap = ImageBitmap.imageResource(R.drawable.ichimatsu)
    val shaderBrush = remember(imageBitmap) {
        ShaderBrush(
            ImageShader(
                imageBitmap, TileMode.Repeated, TileMode.Repeated
            )
        )
    }

    Spacer(
        Modifier
            .fillMaxSize()
            .background(shaderBrush)
    )
}
