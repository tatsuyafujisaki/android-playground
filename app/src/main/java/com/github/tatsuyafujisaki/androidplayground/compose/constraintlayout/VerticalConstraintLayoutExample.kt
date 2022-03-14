package com.github.tatsuyafujisaki.androidplayground.compose.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview(showBackground = true)
@Composable
fun VerticalConstraintLayoutExample() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (text1,
            text2,
            text3,
            text4
        ) = createRefs()

        Text(
            text = "Top",
            modifier = Modifier
                .background(Color.Red)
                .constrainAs(text1) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                }
        )

        Text(
            text = "Top2",
            modifier = Modifier
                .background(Color.Red)
                .constrainAs(text2) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(text1.bottom, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                }
        )

        Text(
            text = "Center",
            modifier = Modifier
                .background(Color.Green)
                .constrainAs(text3) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                }
        )
        Text(
            text = "Bottom",
            modifier = Modifier
                .background(Color.Blue)
                .constrainAs(text4) {
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                }
        )
    }
}
