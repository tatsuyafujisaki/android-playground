package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object VibrationUtil {
    private fun getVibrator(context: Context) =
        if (Build.VERSION.SDK_INT >= VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

    fun vibrate(context: Context, milliseconds: Long = 1000) {
        getVibrator(context).vibrate(
            // The following error is thrown when if you pass [VibrationEffect.EFFECT_CLICK].
            // [IllegalArgumentException]: amplitude must either be DEFAULT_AMPLITUDE, or between 1 and 255 inclusive (amplitude=0)
            VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    }
}
