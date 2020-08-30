package com.github.tatsuyafujisaki.androidplayground

import android.util.Log
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

val okHttpClient: OkHttpClient =
    RetrofitClient
        .okHttpBuilder
        /**
         * Logging interceptors must be added after custom interceptors.
         * Otherwise, headers added by the custom interceptor will not be logged.
         */
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        })
        // The following interceptor is useful to look into malformed JSON.
        .addInterceptor {
            it.proceed(it.request()).apply {
                body?.string()?.let { json ->
                    Log.w(this::class.java.simpleName, json)
                }
            }
        }
        .addNetworkInterceptor(StethoInterceptor())
        .addNetworkInterceptor(FlipperOkhttpInterceptor(MainApplication.networkFlipperPlugin))
        .build()
