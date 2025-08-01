package com.github.tatsuyafujisaki.androidplayground.flow

import android.os.CountDownTimer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@Suppress("unused")
class CountDownTimerWrapper(total: Long, interval: Long, onFinish: () -> Unit) {
    private lateinit var countDownTimer: CountDownTimer

    val flow =
        callbackFlow {
            countDownTimer =
                object : CountDownTimer(total, interval) {
                    override fun onTick(millisUntilFinished: Long) {
                        trySend(millisUntilFinished)
                    }

                    override fun onFinish() {
                        onFinish()
                    }
                }

            awaitClose { countDownTimer.cancel() }
        }

    fun start() {
        countDownTimer.start()
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
