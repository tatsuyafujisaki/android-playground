package com.github.tatsuyafujisaki.androidplayground.enums

import androidx.annotation.StringRes
import com.github.tatsuyafujisaki.androidplayground.R

@Suppress("unused")
enum class Fruit(
    @StringRes val id: Int,
) {
    APPLE(id = R.string.apple),
    ORANGE(id = R.string.orange),
}
