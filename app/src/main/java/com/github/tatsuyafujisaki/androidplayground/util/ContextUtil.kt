package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.Toast
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.github.tatsuyafujisaki.androidplayground.R
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader

object ContextUtil {
    private val tag = this::class.java.simpleName

    fun isInteractive(context: Context) =
        (context.getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive

    fun readAssetAsString(context: Context, fileName: String) =
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readText)

    fun openInBrowser(context: Context, url: String) =
        startActivity(context, Intent(Intent.ACTION_VIEW, url.toUri()), null)

    // Usage: logScreenInfo(resources.displayMetrics)
    fun logScreenInfo(displayMetrics: DisplayMetrics) {
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels
        val densityDpi = displayMetrics.densityDpi
        val density = displayMetrics.density

        require(density == densityDpi / 160f)

        val densityQualifier = displayMetrics.densityDpi.let {
            when {
                it <= DisplayMetrics.DENSITY_LOW -> "ldpi (~120dpi)"
                it <= DisplayMetrics.DENSITY_MEDIUM -> "mdpi (121~160dpi)"
                it <= DisplayMetrics.DENSITY_HIGH -> "hdpi (161~240dpi)"
                it <= DisplayMetrics.DENSITY_XHIGH -> "xhdpi (241~320dpi)"
                it <= DisplayMetrics.DENSITY_XXHIGH -> "xxhdpi (321~480dpi)"
                it <= DisplayMetrics.DENSITY_XXXHIGH -> "xxxhdpi (481~640dpi)"
                else ->
                    throw IllegalArgumentException("Screen quantifier for $it is not supported.")
            }
        }

        Log.i(tag, "dpi: $densityDpi")
        Log.i(tag, "density [(# of 160px) / inch] (= dpi / 160): $density")
        Log.i(tag, "densityQualifier: $densityQualifier")
        Log.i(tag, "widthPixels: $widthPixels")
        Log.i(tag, "heightPixels: $heightPixels")
        Log.i(
            tag, "widthInDp [(1 / 160) inch] (= px / density): " +
            "${(widthPixels / density).toInt()}"
        )
        Log.i(
            tag, "heightInDp [(1 / 160) inch] (= px / density): " +
            "${(heightPixels / density).toInt()}"
        )
    }

    /**
     * "Android also provides a Toast class with a similar API that can be used
     * for displaying system-level notifications.
     * Generally, snackbars are the preferred mechanism for displaying feedback messages to users,
     * as they can be displayed in the context of the UI where the action occurred."
     * quoted from https://material.io/develop/android/components/snackbar
     */
    fun snackBar(view: View, text: String) = Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()

    fun toast(context: Context, text: String) =
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    fun <T> createChip(context: Context, text: String, tag: T): Chip {
        return Chip(context).apply {
            this.text = text
            this.tag = tag
            chipBackgroundColor = getColorStateList(context, R.color.chip_background_selector)
            chipStrokeColor = getColorStateList(context, R.color.chip_stroke_selector)
            chipStrokeWidth = 1f
            isCheckable = true
            isCheckedIconVisible = false
            textAlignment = TEXT_ALIGNMENT_CENTER
            setTextColor(getColorStateList(context, R.color.chip_text_selector))
        }
    }
}
