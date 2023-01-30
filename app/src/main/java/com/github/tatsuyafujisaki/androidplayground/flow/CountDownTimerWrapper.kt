package com.github.tatsuyafujisaki.androidplayground.flow

import android.os.CountDownTimer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class CountDownTimerWrapper(
    millisInFuture: Long,
    countDownInterval: Long
) {
    lateinit var countDownTimer: CountDownTimer

    val flow = callbackFlow {
        countDownTimer =
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

    fun start() {
        countDownTimer.start()
    }

    fun cancel() {
        countDownTimer.cancel()
    }
}

private suspend fun main() = coroutineScope {
    val countDownTimerWrapper = CountDownTimerWrapper(3_000, 1_000)

    launch {
        countDownTimerWrapper.flow.collect {
            println("collect: $it")
        }
    }

    delay(1_000)
    countDownTimerWrapper.start()
}
