package com.github.tatsuyafujisaki.androidplayground.enum

import androidx.annotation.StringRes
import com.github.tatsuyafujisaki.androidplayground.R

enum class Fruit(@StringRes val resId: Int) {
    APPLE(R.string.apple),
    ORANGE(R.string.orange);
}
