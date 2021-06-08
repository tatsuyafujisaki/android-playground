package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.github.tatsuyafujisaki.androidplayground.BuildConfig
import com.github.tatsuyafujisaki.androidplayground.R
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader

object ContextUtil {
    val Context.isInteractive
        get() = (getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive

    fun Context.readAsset(fileName: String) =
        assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readText)

    fun Context.openInBrowser(url: String) =
        startActivity(this, Intent(Intent.ACTION_VIEW, url.toUri()), null)

    // Usage: logScreenInfo(resources.displayMetrics)
    fun DisplayMetrics.logScreenInfo() {
        require(density == densityDpi / 160f)

        val densityQualifier = densityDpi.let {
            when {
                it <= DisplayMetrics.DENSITY_LOW -> "ldpi (~120dpi)"
                it <= DisplayMetrics.DENSITY_MEDIUM -> "mdpi (121~160dpi)"
                it <= DisplayMetrics.DENSITY_HIGH -> "hdpi (161~240dpi)"
                it <= DisplayMetrics.DENSITY_XHIGH -> "xhdpi (241~320dpi)"
                it <= DisplayMetrics.DENSITY_XXHIGH -> "xxhdpi (321~480dpi)"
                it <= DisplayMetrics.DENSITY_XXXHIGH -> "xxxhdpi (481~640dpi)"
                else -> error("Screen quantifier for $it is not supported.")
            }
        }

        val tag = "LogScreenInfo"

        Log.d(tag, "dpi: $densityDpi")
        Log.d(tag, "density [(# of 160px) / inch] (= dpi / 160): $density")
        Log.d(tag, "densityQualifier: $densityQualifier")
        Log.d(tag, "widthPixels: $widthPixels")
        Log.d(tag, "heightPixels: $heightPixels")
        Log.d(tag, "widthInDp [(1 / 160) inch] (= px / density): " + "${(widthPixels / density).toInt()}")
        Log.d(tag, "heightInDp [(1 / 160) inch] (= px / density): " + "${(heightPixels / density).toInt()}")
    }

    /**
     * "Android also provides a Toast class with a similar API that can be used
     * for displaying system-level notifications.
     * Generally, snackbars are the preferred mechanism for displaying feedback messages to users,
     * as they can be displayed in the context of the UI where the action occurred."
     * quoted from https://material.io/develop/android/components/snackbar
     */
    fun View.snackbar(@StringRes resId: Int) =
        Snackbar.make(this, resId, Snackbar.LENGTH_SHORT).show()

    fun View.snackbar(text: String) =
        Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()

    fun Context.toast(@StringRes resId: Int) =
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

    fun Context.toast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    fun <T> Context.createChip(text: String, tag: T): Chip {
        return Chip(this).apply {
            this.text = text
            this.tag = tag
            chipBackgroundColor = getColorStateList(context, R.color.chip_background_selector)
            chipStrokeColor = getColorStateList(context, R.color.chip_stroke_selector)
            chipStrokeWidth = 1f
            isCheckable = true
            isCheckedIconVisible = false
            textAlignment = TEXT_ALIGNMENT_CENTER
            setTextColor(getColorStateList(context, R.color.checked_state_selector))
        }
    }

    fun MenuItem.color(@ColorInt color: Int) {
        title = SpannableString(title.toString()).apply {
            setSpan(ForegroundColorSpan(color), 0, length, 0)
        }
    }

    fun MenuItem.color(context: Context, @ColorRes id: Int) {
        title = SpannableString(title.toString()).apply {
            setSpan(ForegroundColorSpan(ContextCompat.getColor(context, id)), 0, length, 0)
        }
    }

    fun MenuItem.color2(activity: Activity, @ColorInt color: Int) {
        activity.findViewById<TextView>(itemId)?.setTextColor(color)
    }

    fun MenuItem.color3(fragment: Fragment, @ColorInt color: Int) {
        fragment.view?.findViewById<TextView>(itemId)?.setTextColor(color)
    }

    /**
     * https://developer.android.com/distribute/marketing-tools/linking-to-google-play#android-app
     */
    fun Context.openGooglePlay() {
        Intent(Intent.ACTION_VIEW).apply {
            data = ("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID).toUri()
            setPackage("com.android.vending")
        }.let(::startActivity)
    }
}
