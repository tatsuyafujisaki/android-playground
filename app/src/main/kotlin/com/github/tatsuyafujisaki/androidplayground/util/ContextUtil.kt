package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PackageInfoFlags
import android.content.res.Resources
import android.os.Build
import android.provider.Settings
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.MenuItem
import android.view.View.TEXT_ALIGNMENT_CENTER
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.github.tatsuyafujisaki.androidplayground.BuildConfig
import com.github.tatsuyafujisaki.androidplayground.R
import com.google.android.material.chip.Chip
import java.io.BufferedReader

private const val TAG = "ContextUtil"

/**
 * Impractical redundant explanatory wrappers
 */
object ContextUtil {
    object AutoRotation {
        fun isAutoRotatable(context: Context) =
            Settings.System.getInt(
                context.contentResolver, Settings.System.ACCELEROMETER_ROTATION,
            ) == 1
    }

    fun getVersionName(context: Context): String {
        val packageManager = context.packageManager
        val packageName = context.packageName
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageInfoFlags.of(0))
        } else {
            packageManager.getPackageInfo(packageName, 0)
        }.versionName
    }

    /**
     * Read a text file in the "assets" directory.
     * cf. [ResourcesUtil.readResourceAsText]
     */
    fun readAssetAsText(
        context: Context,
        fileName: String,
    ) = context.assets.open(fileName).bufferedReader().use(BufferedReader::readText)

    fun openInBrowser(
        context: Context,
        url: String,
    ) {
        startActivity(context, Intent(Intent.ACTION_VIEW, url.toUri()), null)
    }

    /**
     * https://developer.android.com/distribute/marketing-tools/linking-to-google-play#android-app
     */
    fun openGooglePlay(context: Context) {
        context.startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data =
                    ("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}").toUri()
                setPackage("com.android.vending")
            },
        )
    }

    /**
     * @param displayMetrics is a property of [Resources]
     */
    fun printScreenInfo(displayMetrics: DisplayMetrics) {
        require(displayMetrics.density == displayMetrics.densityDpi / 160f)

        val densityQualifier =
            displayMetrics.densityDpi.let {
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

        Log.d(TAG, "dpi: $${displayMetrics.densityDpi}")
        Log.d(TAG, "density [(# of 160px) / inch] (= dpi / 160): ${displayMetrics.density}")
        Log.d(TAG, "densityQualifier: $densityQualifier")
        Log.d(TAG, "widthPixels: ${displayMetrics.widthPixels}")
        Log.d(TAG, "heightPixels: ${displayMetrics.heightPixels}")
        Log.d(
            TAG,
            "widthInDp [(1 / 160) inch] (= px / density): ${(displayMetrics.widthPixels / displayMetrics.density).toInt()}",
        )
        Log.d(
            TAG,
            "heightInDp [(1 / 160) inch] (= px / density): ${(displayMetrics.heightPixels / displayMetrics.density).toInt()}",
        )
    }

    fun <T> createChip(
        context: Context,
        text: String,
        tag: T,
    ): Chip {
        return Chip(context).apply {
            this.text = text
            this.tag = tag
            chipBackgroundColor = getColorStateList(context, R.color.checked_state_selector)
            chipStrokeColor = getColorStateList(context, R.color.checked_state_selector)
            chipStrokeWidth = 1f
            isCheckable = true
            isCheckedIconVisible = false
            textAlignment = TEXT_ALIGNMENT_CENTER
            setTextColor(getColorStateList(context, R.color.checked_state_selector))
        }
    }

    fun color(
        menuItem: MenuItem,
        @ColorInt color: Int,
    ) {
        menuItem.title =
            SpannableString(menuItem.title.toString()).apply {
                setSpan(ForegroundColorSpan(color), 0, length, 0)
            }
    }

    fun color(
        context: Context,
        menuItem: MenuItem,
        @ColorRes id: Int,
    ) {
        menuItem.title =
            SpannableString(menuItem.title.toString()).apply {
                setSpan(ForegroundColorSpan(ContextCompat.getColor(context, id)), 0, length, 0)
            }
    }
}
