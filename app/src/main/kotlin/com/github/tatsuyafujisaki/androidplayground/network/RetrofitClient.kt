package com.github.tatsuyafujisaki.androidplayground.network

import com.github.tatsuyafujisaki.androidplayground.sample.OkHttpSample.addExtraHeadersInterceptor
import com.github.tatsuyafujisaki.androidplayground.sample.OkHttpSample.addLoggingInterceptor
import com.github.tatsuyafujisaki.androidplayground.sample.OkHttpSample.addPeekingRequestInterceptor
import com.github.tatsuyafujisaki.androidplayground.sample.OkHttpSample.addPeekingResponseInterceptor
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
                    .build(),
            )
            .addConverterFactory(
                Json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(JsonPlaceholderService::class.java)
}
