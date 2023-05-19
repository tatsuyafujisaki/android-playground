package com.github.tatsuyafujisaki.androidplayground.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NullableLambdaProvider : PreviewParameterProvider<(() -> Unit)?> {
    override val values = sequenceOf({}, null)
}
