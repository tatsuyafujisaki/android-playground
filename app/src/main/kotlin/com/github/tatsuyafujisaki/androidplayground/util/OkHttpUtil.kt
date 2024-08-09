package com.github.tatsuyafujisaki.androidplayground.util

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
    fun OkHttpClient.Builder.addExtraHeadersInterceptor() = addInterceptor {
        val request = it.request().newBuilder()
            .addHeader("X-BuildManufacturer", Build.MANUFACTURER)
            .addHeader("X-Brand", Build.BRAND)
            .addHeader("X-Product", Build.PRODUCT)
            .addHeader("X-BuildModel", Build.MODEL)
            // Android OS version
            .addHeader("X-BuildVersionRelease", Build.VERSION.RELEASE)
            .addHeader("X-VersionName", BuildConfig.VERSION_NAME)
            .build()
        it.proceed(request)
    }

    /**
     * Logging interceptors must be added after custom interceptors. Otherwise, headers added by the custom interceptor will not be logged.
     */
    fun OkHttpClient.Builder.addLoggingInterceptor() = addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        },
    )

    fun OkHttpClient.Builder.addPeekingRequestInterceptor() = addInterceptor {
        it.proceed(it.request()).apply {
            val request = it.request()
            val httpUrl = request.url

            Log.d("👀request", request.toString())
            Log.d("👀request > url ", httpUrl.toString())
            Log.d("👀request > url > encodedPath", httpUrl.encodedPath)
            Log.d("👀request > url > pathSegments", httpUrl.pathSegments.toString())
            Log.d(
                "👀request > url > encodedPathSegments",
                httpUrl.encodedPathSegments.toString(),
            )
            Log.d("👀request > url > encodedQuery", httpUrl.encodedQuery.toString())
            Log.d(
                "👀request > url > queryParameterNames",
                httpUrl.queryParameterNames.toString(),
            )
            Log.d(
                "👀request > url > queryParameterValues",
                httpUrl.queryParameterNames.flatMap(httpUrl::queryParameterValues).toString(),
            )
            Log.d("👀request > method", request.method)
            Log.d("👀request > headers", request.headers.toString())
            Log.d("👀request > body", request.body.toString())
            Log.d("👀request > tag", request.tag().toString())
        }
    }

    fun OkHttpClient.Builder.addPeekingResponseInterceptor() = addInterceptor {
        val response = it.proceed(it.request())
        Log.d("👀response", response.toString())
        Log.d("👀response > protocol", response.protocol.toString())
        Log.d("👀response > message", response.message)
        Log.d("👀response > code", response.code.toString())
        Log.d("👀response > headers", response.headers.toString())
        // Don't call "response.body?.string()".
        // It will cause the "java.lang.IllegalStateException: closed" error because ...
        // > The response body can be consumed only once.
        // https://square.github.io/okhttp/5.x/okhttp/okhttp3/-response-body/
        Log.d("👀response > body", response.peekBody(Long.MAX_VALUE).string())
        response
    }

    fun OkHttpClient.Builder.addCookieJar() = apply {
        cookieJar(
            object : CookieJar {
                // Sets cookies from CookieManger to HTTP requests.
                override fun loadForRequest(url: HttpUrl) =
                    CookieManager.getInstance().getCookie(url.toString())?.split(';')?.mapNotNull {
                        Cookie.parse(
                            url = UrlUtil.OkHttpUrl.getBaseUrl(url),
                            setCookie = it.trim() /* key=value */
                        )
                    }.orEmpty()

                // Stores cookies from HTTP responses in CookieManager.
                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    cookies.forEach {
                        CookieManager.getInstance().setCookie(url.host, "${it.name}=${it.value}")
                    }
                }
            },
        )
    }
}
