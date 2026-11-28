package com.github.tatsuyafujisaki.androidplayground.flow

import kotlin.time.Duration
import kotlin.time.TimeSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Emits the remaining time immediately and then every [interval] until [total] has elapsed.
 *
 * The flow is cold, so each collector gets its own countdown that starts on collection and stops
 * when the collector is canceled. The flow completes when the countdown finishes.
 *
 * Usage:
 * ```
 * lifecycleScope.launch {
 *     countDownFlow(total = 10.seconds, interval = 1.seconds)
 *         .onCompletion { cause -> if (cause == null) Log.d(TAG, "Finished") }
 *         .collect { Log.d(TAG, "Remaining: $it") }
 * }
 * ```
 */
@Suppress("unused")
fun countDownFlow(total: Duration, interval: Duration): Flow<Duration> {
    require(interval.isPositive()) { "interval must be positive." }
    return flow {
        val end = TimeSource.Monotonic.markNow() + total
        while (true) {
            val remaining = -end.elapsedNow()
            if (!remaining.isPositive()) break
            emit(remaining)
            delay(minOf(interval, remaining))
        }
    }
}
