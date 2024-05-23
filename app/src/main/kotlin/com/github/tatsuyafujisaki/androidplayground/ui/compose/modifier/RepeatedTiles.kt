package com.github.tatsuyafujisaki.androidplayground.ui.compose.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
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
private fun Modifier.repeatedTiles(): Modifier =
    composed {
        val imageBitmap = ImageBitmap.imageResource(R.drawable.ichimatsu)
        val shaderBrush =
            remember(imageBitmap) {
                ShaderBrush(
                    ImageShader(
                        image = imageBitmap,
                        tileModeX = TileMode.Repeated,
                        tileModeY = TileMode.Repeated,
                    ),
                )
            }
        background(shaderBrush)
    }

@Preview
@Composable
private fun RepeatedTileSpacerExample() {
    Spacer(
        Modifier
            .repeatedTiles()
            .fillMaxSize(),
    )
}

@Preview
@Composable
private fun RepeatedTileBoxPreview() {
    Box(
        modifier =
        Modifier
            .repeatedTiles()
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Hello", color = Color.White)
    }
}
