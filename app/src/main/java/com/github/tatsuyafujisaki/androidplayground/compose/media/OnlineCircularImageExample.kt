package com.github.tatsuyafujisaki.androidplayground.compose.media

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private fun <T> T.applyIf(condition: Boolean, block: T.() -> T) = if (condition) block() else this

@Composable
fun OnlineCircularImageExample(
    modifier: Modifier = Modifier,
    url: String,
    border: Pair<Dp, Color>? = null
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .applyIf(border != null) {
                border(border!!.first, border.second, CircleShape)
            },
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun PreviewOnlineCircularImageExample() {
    OnlineCircularImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://2.bp.blogspot.com/-3J8MukWVApM/XLAcyuflY4I/AAAAAAABSTA/3IxtQnJKJH48h42rvdg2tGrQEvsc4QxrQCLcBGAs/s800/bg_ocean_suiheisen.jpg"
    )
}

@Preview
@Composable
private fun PreviewOnlineCircularBorderedImageExample() {
    OnlineCircularImageExample(
        modifier = Modifier.size(200.dp),
        url = "https://2.bp.blogspot.com/-3J8MukWVApM/XLAcyuflY4I/AAAAAAABSTA/3IxtQnJKJH48h42rvdg2tGrQEvsc4QxrQCLcBGAs/s800/bg_ocean_suiheisen.jpg",
        border = 8.dp to Color.Red
    )
}
