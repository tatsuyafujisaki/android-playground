package com.github.tatsuyafujisaki.androidplayground.sample

import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.PowerManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

enum class MyNetworkType {
    WIFI,
    CELLULAR,
    UNKNOWN,
}

fun getMemoryInfo(context: Context): ActivityManager.MemoryInfo =
    ActivityManager.MemoryInfo().also {
        context.getSystemService<ActivityManager>()!!.getMemoryInfo(it)
    }

fun getInputMethodManager(context: Context) = context.getSystemService<InputMethodManager>()!!

fun getNetworkType(context: Context): MyNetworkType {
    val capabilities = getActiveNetworkCapabilities(context)

    return when {
        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> MyNetworkType.WIFI
        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> MyNetworkType.CELLULAR
        else -> MyNetworkType.UNKNOWN
    }
}

fun isNetworkAvailable(context: Context) =
    getActiveNetworkCapabilities(context)?.run {
        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    } ?: false

fun isInteractive(context: Context) = context.getSystemService<PowerManager>()!!.isInteractive

private fun getActiveNetworkCapabilities(context: Context): NetworkCapabilities? =
    with(context.getSystemService<ConnectivityManager>()!!) {
        getNetworkCapabilities(activeNetwork)
    }
