package com.github.tatsuyafujisaki.androidplayground.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlin.time.Duration

class LifecycleAwareTimer {
    private var job: Job? = null

    fun launch(
        scope: CoroutineScope,
        period: Duration,
        action: suspend () -> Unit,
    ) {
        job?.cancel()
        job =
            flow<Nothing> {
                while (true) {
                    action()
                    delay(period)
                }
            }.launchIn(scope)
    }

    fun cancel() {
        job?.cancel()
    }
}
