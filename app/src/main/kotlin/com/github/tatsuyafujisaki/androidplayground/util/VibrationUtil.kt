package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object VibrationUtil {
    private fun getVibrator(context: Context) = if (Build.VERSION.SDK_INT >= VERSION_CODES.S) {
        (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
    } else {
        @Suppress("DEPRECATION") context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private fun getVibrationEffect() = if (Build.VERSION.SDK_INT >= VERSION_CODES.Q) {
        VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
    } else {
        VibrationEffect.createOneShot(1_000, VibrationEffect.DEFAULT_AMPLITUDE)
    }

    fun vibrate(context: Context) {
        getVibrator(context).vibrate(getVibrationEffect())
    }
}
