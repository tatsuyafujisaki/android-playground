package com.github.tatsuyafujisaki.androidplayground.network

import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addCookieJar
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addExtraHeadersInterceptor
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addLoggingInterceptor
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addPeekingRequestInterceptor
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addPeekingResponseInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    fun createJsonPlaceholderService(): JsonPlaceholderService =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(
                OkHttpClient
                    .Builder()
                    .addExtraHeadersInterceptor()
                    .addLoggingInterceptor()
                    .addPeekingResponseInterceptor()
                    .addPeekingRequestInterceptor()
                    .addCookieJar()
                    .build(),
            )
            .addConverterFactory(
                Json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(JsonPlaceholderService::class.java)
}
