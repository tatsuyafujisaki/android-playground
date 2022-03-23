package com.github.tatsuyafujisaki.androidplayground.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow

object FlowUtil {
    fun <T> buildFlow(x: T, interval: Long = 1000) = flow {
        while (true) {
            emit(x)
            delay(interval)
        }
    }.distinctUntilChanged()
}
