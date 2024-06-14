package com.github.tatsuyafujisaki.androidplayground.ui.compose.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
        val (text1, text2, icon) = createRefs()

        val titleAndSubtitleEndBarrier = createEndBarrier(text1, text2)

        Text(
            text = "Apple",
            modifier = Modifier
                .background(color = Color.Red)
                .constrainAs(text1) {
                    start.linkTo(anchor = parent.start)
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = text2.top)
                },
        )

        Text(
            text = "Orange",
            modifier = Modifier
                .background(color = Color.Red)
                .constrainAs(text2) {
                    start.linkTo(anchor = parent.start)
                    top.linkTo(anchor = text1.bottom)
                    bottom.linkTo(anchor = parent.bottom)
                },
        )

        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
            modifier = Modifier
                .background(color = Color.Green)
                .constrainAs(icon) {
                    width = Dimension.fillToConstraints
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = titleAndSubtitleEndBarrier)
                    bottom.linkTo(anchor = parent.bottom)
                },
        )
    }
}
