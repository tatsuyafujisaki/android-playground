package com.github.tatsuyafujisaki.androidplayground.flow

import android.os.CountDownTimer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class CountDownTimerWrapper(
    millisInFuture: Long,
    countDownInterval: Long,
    onFinish: () -> Unit
) {
    private lateinit var countDownTimer: CountDownTimer

    val flow = callbackFlow {
        countDownTimer =
            object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    trySend(millisUntilFinished)
                }

                override fun onFinish() {
                    onFinish()
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

// Usage:
/*
    override fun onCreateView(...): View {
        val countDownTimerWrapper = CountDownTimerWrapper(10_000, 1_000) {
            println("onFinish")
        }

        lifecycleScope.launch {
            countDownTimerWrapper.flow.collect {
                println("collect: $it")
            }
        }

        lifecycleScope.launch {
            countDownTimerWrapper.start()
        }

        // ...
    }
 */
