package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.os.Build
import android.util.Log
import android.webkit.CookieManager
import com.github.tatsuyafujisaki.androidplayground.BuildConfig
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpUtil {
    /**
     * Add this interpreter only when debugging a raw JSON string in the response body.
     * Adding this interpreter causes the "java.lang.IllegalStateException: closed" error because ...
     * > The response body can be consumed only once.
     * https://square.github.io/okhttp/3.x/okhttp/okhttp3/ResponseBody.html
     */
    private fun OkHttpClient.Builder.addLoggingRawJsonInterceptor() {
        addInterceptor {
            it.proceed(it.request()).apply {
                // JSON will be logged with the prefix "D/Response".
                Log.d(this::class.java.simpleName, body?.string().toString())
            }
        }
    }

    fun OkHttpClient.Builder.addInterceptors(context: Context) = apply {
        addInterceptor {
            it.proceed(
                it
                    .request()
                    .newBuilder()
                    // Each header added by an interceptor can be overwritten
                    // by passing the same header when calling an API.
                    .addHeader("X-BuildManufacturer", Build.MANUFACTURER)
                    .addHeader("X-Brand", Build.BRAND)
                    .addHeader("X-Product", Build.PRODUCT)
                    .addHeader("X-BuildModel", Build.MODEL)
                    // Android OS version
                    .addHeader("X-BuildVersionRelease", Build.VERSION.RELEASE)
                    .addHeader("X-VersionName", BuildConfig.VERSION_NAME)
                    .build()
            )
        }
        if (BuildConfig.DEBUG) {
            // addLoggingRawJsonInterceptor()
            /**
             * Logging interceptors must be added after custom interceptors.
             * Otherwise, headers added by the custom interceptor will not be logged.
             */
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.NONE
                }
            )
        }
    }

    fun OkHttpClient.Builder.addCookieJar() = apply {
        cookieJar(object : CookieJar {
            // Set cookies in CookieManger to HTTP requests
            override fun loadForRequest(url: HttpUrl): List<Cookie> =
                CookieManager
                    .getInstance()
                    .getCookie(url.toString())
                    ?.split(';')
                    ?.filter {
                        isKeyOfInterest(it.split('=').firstOrNull()?.trim())
                    }
                    ?.mapNotNull {
                        Cookie.parse(url.baseUrl, it.trim() /* key=value */)
                    }.orEmpty()

            // Save cookies in HTTP responses to CookieManger
            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookies.filter {
                    isKeyOfInterest(it.name)
                }.forEach {
                    CookieManager
                        .getInstance()
                        .setCookie(url.host, "${it.name}=${it.value}")
                }
            }
        })
    }

    private fun isKeyOfInterest(cookieKey: String?) =
        setOf("SampleCookieKey1", "SampleCookieKey1").contains(cookieKey)

    val HttpUrl.baseUrl get() = HttpUrl.Builder().scheme(scheme).host(host).build()
}
