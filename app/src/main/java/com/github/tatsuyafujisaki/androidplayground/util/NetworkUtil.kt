package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.HttpUrl

object NetworkUtil {
    val Context.isNetworkAvailable
        get() = with(getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

    private val HttpUrl.baseUrl
        get() = HttpUrl.Builder().scheme(scheme).host(host).build()
}
