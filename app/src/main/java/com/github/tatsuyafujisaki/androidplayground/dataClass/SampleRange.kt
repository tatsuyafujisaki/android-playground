package com.github.tatsuyafujisaki.androidplayground.dataclass

/**
 * @IntRange must be fully qualified or Kotlin confuses it with [kotlin.ranges.IntRange].
 * On a separate note, you cannot validate "lower <= upper" with @IntRange
 * because @IntRange can take only compile-time constants.
 */
data class SampleRange(
    @androidx.annotation.IntRange(from = MIN, to = MAX) val lower: Int,
    @androidx.annotation.IntRange(from = MIN, to = MAX) val upper: Int
) {
    init {
        require(lower <= upper)
    }

    companion object {
        const val MIN = 0L
        const val MAX = 10000L
    }
}
