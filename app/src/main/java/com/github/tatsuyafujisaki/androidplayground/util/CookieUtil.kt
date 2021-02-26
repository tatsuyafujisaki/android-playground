package com.github.tatsuyafujisaki.androidplayground.util

import android.webkit.CookieManager

object CookieUtil {
    fun CookieManager.getCookies(url: String): Map<String, String> =
        getCookie(url)
            ?.split(';')
            ?.map {
                val (key, value) = it.split('=')
                key.trim() to value.trim()
            }?.toMap()
            .orEmpty()

    fun CookieManager.setCookies(url: String, cookies: Map<String, String>) {
        for (cookie in cookies) {
            setCookie(url, "${cookie.key}=${cookie.value}")
        }
    }
}

