package com.github.tatsuyafujisaki.androidplayground.data

import androidx.annotation.FloatRange

/**
 * NB: You cannot validate "lower <= upper" with [FloatRange] because [FloatRange] can only take compile-time constants.
 */
data class FloatRangeExample(
    @FloatRange(from = MIN, to = MAX) val lower: Float,
    @FloatRange(from = MIN, to = MAX) val upper: Float
) {
    init {
        require(lower <= upper)
    }

    companion object {
        const val MIN = -100.0
        const val MAX = 100.0
    }
}
