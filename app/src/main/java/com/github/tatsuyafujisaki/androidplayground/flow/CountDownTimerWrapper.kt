package com.github.tatsuyafujisaki.androidplayground.flow

import android.os.CountDownTimer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class CountDownTimerWrapper {
    lateinit var countDownTimer: CountDownTimer

    fun start() {
        countDownTimer.start()
    }

    fun cancel() {
        countDownTimer.cancel()
    }
}

private fun flowFrom(
    countDownTimerWrapper: CountDownTimerWrapper,
    millisInFuture: Long,
    countDownInterval: Long
) = callbackFlow {
    countDownTimerWrapper.countDownTimer =
        object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                trySend(millisUntilFinished)
            }

            override fun onFinish() {
                println("onFinish")
            }
        }
    awaitClose {}
}

private suspend fun main() = coroutineScope {
    val countDownTimerWrapper = CountDownTimerWrapper()

    launch {
        flowFrom(countDownTimerWrapper, 3_000, 1_000).collect {
            println("collect: $it")
        }
    }

    countDownTimerWrapper.start()
}
