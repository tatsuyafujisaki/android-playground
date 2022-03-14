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

@Preview(showBackground = true)
@Composable
fun HorizontalConstraintLayoutExample() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (text1, text2) = createRefs()

        Text(
            text = "Hello",
            modifier = Modifier
                .background(Color.Blue)
                .constrainAs(text1) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(text2.start, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                }
        )
        Text(
            text = "World",
            modifier = Modifier
                .background(Color.Red)
                .constrainAs(text2) {
                    start.linkTo(text1.end, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                }
        )
    }
}
