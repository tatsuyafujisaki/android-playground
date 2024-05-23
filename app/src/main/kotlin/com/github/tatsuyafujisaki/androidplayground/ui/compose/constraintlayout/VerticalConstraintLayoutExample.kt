package com.github.tatsuyafujisaki.androidplayground.ui.compose.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
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
        val (slider1, slider2, text1, text2, text3, text4) = createRefs()

        // "width = Dimension.fillToConstraints" is NOT set.
        Slider(
            value = 0f,
            onValueChange = {},
            modifier =
            Modifier
                .constrainAs(slider1) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                },
        )

        // "width = Dimension.fillToConstraints" is set.
        Slider(
            value = 0f,
            onValueChange = {},
            modifier =
            Modifier
                .constrainAs(slider2) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(slider1.bottom, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                },
        )

        Text(
            text = "fillToConstraints is NOT set.",
            modifier =
            Modifier
                .background(Color.Red)
                .constrainAs(text1) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(slider2.bottom)
                    end.linkTo(parent.end, 16.dp)
                },
        )

        Text(
            text = "fillToConstraints is set.",
            modifier =
            Modifier
                .background(Color.Red)
                .constrainAs(text2) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    top.linkTo(text1.bottom)
                    end.linkTo(parent.end)
                },
        )

        Text(
            text = "Center",
            modifier =
            Modifier
                .background(Color.Green)
                .constrainAs(text3) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        )
        Text(
            text = "Bottom",
            modifier =
            Modifier
                .background(Color.Blue)
                .constrainAs(text4) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        )
    }
}
