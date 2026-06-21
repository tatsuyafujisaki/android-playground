package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PackageInfoFlags
import android.provider.Settings
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import java.io.BufferedReader

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
}
