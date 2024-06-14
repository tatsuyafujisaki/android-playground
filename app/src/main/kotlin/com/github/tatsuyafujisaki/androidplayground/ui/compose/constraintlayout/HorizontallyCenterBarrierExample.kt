package com.github.tatsuyafujisaki.androidplayground.ui.compose.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun HorizontallyCenterBarrierExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (ref) = createRefs()

        val horizontallyCenterGuideline = createGuidelineFromStart(fraction = 0.5f)

        Text(
            text = "Hello, World",
            modifier = Modifier
                .background(color = Color.Cyan)
                .constrainAs(ref = ref) {
                    start.linkTo(anchor = horizontallyCenterGuideline)
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                },
        )
    }
}
