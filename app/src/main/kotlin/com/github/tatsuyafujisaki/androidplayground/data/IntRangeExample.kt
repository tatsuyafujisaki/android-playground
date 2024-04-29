package com.github.tatsuyafujisaki.androidplayground.data

import androidx.annotation.IntRange

/**
 * NB: You cannot validate "lower <= upper" with [IntRange] because [IntRange] can only take compile-time constants.
 */
data class IntRangeExample(
    @IntRange(from = MIN, to = MAX) val lower: Int,
    @IntRange(from = MIN, to = MAX) val upper: Int,
) {
    init {
        require(lower <= upper)
    }

    companion object {
        const val MIN = -100L
        const val MAX = 100L
    }
}
