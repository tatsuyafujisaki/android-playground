package com.github.tatsuyafujisaki.androidplayground.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow

object FlowUtil {
    fun <T> buildFlow(interval: Long = 1000, emitter: () -> T) = flow {
        while (true) {
            emit(emitter())
            delay(interval)
        }
    }.distinctUntilChanged()
}
