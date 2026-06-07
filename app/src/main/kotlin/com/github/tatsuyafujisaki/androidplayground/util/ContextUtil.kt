package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PackageInfoFlags
import android.provider.Settings
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import java.io.BufferedReader

private const val TAG = "ContextUtil"

/**
 * Impractical redundant explanatory wrappers
 */
@Suppress("unused")
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
        return packageManager.getPackageInfo(packageName, PackageInfoFlags.of(0)).versionName ?: ""
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
                    ("https://play.google.com/store/apps/details?id=${context.packageName}").toUri()
                setPackage("com.android.vending")
            },
        )
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
