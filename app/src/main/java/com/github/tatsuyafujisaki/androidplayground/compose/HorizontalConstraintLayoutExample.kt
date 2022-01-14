package com.github.tatsuyafujisaki.androidplayground.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun HorizontalConstraintLayoutExample(height: Dp = 100.dp) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val (text1, text2) = createRefs()

        Text(
            text = "Hello",
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(parent.start, 8.dp)
                top.linkTo(parent.top, 8.dp)
                end.linkTo(text2.start, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
            }
        )
        Text(
            text = "World",
            modifier = Modifier.constrainAs(text2) {
                start.linkTo(text1.end, 8.dp)
                top.linkTo(parent.top, 8.dp)
                end.linkTo(parent.end, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
            }
        )
    }
}
