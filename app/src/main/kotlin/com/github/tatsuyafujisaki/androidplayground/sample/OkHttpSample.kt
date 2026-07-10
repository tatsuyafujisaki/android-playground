package com.github.tatsuyafujisaki.androidplayground.util

import android.os.Build
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpUtil {
    fun OkHttpClient.Builder.addExtraHeadersInterceptor() = addInterceptor {
        val request = it.request().newBuilder()
            .addHeader("X-Brand", Build.BRAND)
            .addHeader("X-Manufacturer", Build.MANUFACTURER)
            .addHeader("X-Model", Build.MODEL)
            .addHeader("X-Product", Build.PRODUCT)
            .addHeader("X-Version-Release", Build.VERSION.RELEASE)
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
}
