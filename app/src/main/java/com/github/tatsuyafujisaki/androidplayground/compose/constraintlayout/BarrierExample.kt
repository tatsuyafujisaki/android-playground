package com.github.tatsuyafujisaki.androidplayground.compose.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview(showBackground = true)
@Composable
fun BarrierExample() {
    ConstraintLayout {
        val (
            title,
            subtitle,
            icon
        ) = createRefs()

        val titleAndSubtitleEndBarrier = createEndBarrier(title, subtitle)

        Text(
            text = "Title",
            modifier = Modifier
                .background(Color.Red)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(subtitle.top)
                }
        )

        Text(
            text = "Subtitle",
            modifier = Modifier
                .background(Color.Red)
                .constrainAs(subtitle) {
                    start.linkTo(parent.start)
                    top.linkTo(title.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )

        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .background(Color.Blue)
                .constrainAs(icon) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(titleAndSubtitleEndBarrier)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
