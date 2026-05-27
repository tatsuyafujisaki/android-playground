package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.os.VibrationEffect
import android.os.VibratorManager

@Suppress("unused")
object VibrationUtil {
    private fun getVibrator(context: Context) =
        (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator

    private fun getVibrationEffect() =
        VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)

    fun vibrate(context: Context) {
        getVibrator(context).vibrate(getVibrationEffect())
    }
}
