package com.github.tatsuyafujisaki.androidplayground.util

import android.webkit.CookieManager

object CookieUtil {
    fun getCookies(cookieManager: CookieManager, url: String): Map<String, String> =
        cookieManager
            .getCookie(url)
            ?.split(';')
            ?.map {
                val (key, value) = it.split('=')
                key.trim() to value.trim()
            }?.toMap()
            .orEmpty()

    fun setCookies(cookieManager: CookieManager, url: String, cookies: Map<String, String>) {
        for (cookie in cookies) {
            cookieManager.setCookie(url, "${cookie.key}=${cookie.value}")
        }
    }
}

