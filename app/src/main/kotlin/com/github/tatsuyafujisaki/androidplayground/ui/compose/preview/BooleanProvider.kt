package com.github.tatsuyafujisaki.androidplayground.ui.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Suppress("unused")
class BooleanProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}
