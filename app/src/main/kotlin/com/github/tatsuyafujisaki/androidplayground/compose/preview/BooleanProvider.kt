package com.github.tatsuyafujisaki.androidplayground.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class BooleanProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}
