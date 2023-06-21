package com.github.tatsuyafujisaki.androidplayground.ui.compose.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun PackedChainStyleExample() {
    ConstraintLayout(modifier = Modifier.height(100.dp)) {
        val (text1, text2) = createRefs()

        constrain(createVerticalChain(text1, text2, chainStyle = ChainStyle.Packed)) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        Text(
            text = "Apple",
            modifier = Modifier
                .background(Color.Red)
                .constrainAs(text1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(text2.top)
                }
        )

        Text(
            text = "Orange",
            modifier = Modifier
                .background(Color.Red)
                .constrainAs(text2) {
                    top.linkTo(text1.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
