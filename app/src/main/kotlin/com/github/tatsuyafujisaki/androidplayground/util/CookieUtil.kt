package com.github.tatsuyafujisaki.androidplayground.util

import android.webkit.CookieManager

object CookieUtil {
    fun getCookies(url: String): Map<String, String> {
        val cookie = CookieManager.getInstance().getCookie(url) ?: return emptyMap()

        return cookie.split(';').associate {
            val (key, value) = it.split('=')
            key.trim() to value.trim()
        }
    }

    fun setCookies(
        url: String,
        cookies: Map<String, String>,
    ) {
        val cookieManager = CookieManager.getInstance()
        for (cookie in cookies) {
            cookieManager.setCookie(url, "${cookie.key}=${cookie.value}")
        }
    }
}
