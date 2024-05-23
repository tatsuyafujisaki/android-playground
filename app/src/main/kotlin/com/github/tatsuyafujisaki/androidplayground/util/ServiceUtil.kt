package com.github.tatsuyafujisaki.androidplayground.util

import android.app.ActivityManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.PowerManager
import android.view.inputmethod.InputMethodManager

object ServiceUtil {
    object ActivityService {
        private fun getActivityManager(context: Context) =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        fun getMemoryInfo(context: Context): ActivityManager.MemoryInfo {
            return ActivityManager.MemoryInfo().also {
                getActivityManager(context).getMemoryInfo(it)
            }
        }
    }

    object InputMethodService {
        fun getInputMethodManager(context: Context) =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    object ConnectivityService {
        enum class MyNetworkType {
            WIFI,
            CELLULAR,
            UNKNOWN,
        }

        private fun getConnectivityService(context: Context) =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        fun getNetworkType(context: Context): MyNetworkType {
            val connectivityService = getConnectivityService(context)
            val capabilities =
                connectivityService.getNetworkCapabilities(connectivityService.activeNetwork)

            return when {
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> MyNetworkType.WIFI
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> MyNetworkType.CELLULAR
                else -> MyNetworkType.UNKNOWN
            }
        }

        fun isNetworkAvailable(context: Context) =
            with(getConnectivityService(context)) {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            }
    }

    object PowerService {
        private fun getPowerService(context: Context) =
            context.getSystemService(Context.POWER_SERVICE) as PowerManager

        fun isInteractive(context: Context) = getPowerService(context).isInteractive
    }
}
