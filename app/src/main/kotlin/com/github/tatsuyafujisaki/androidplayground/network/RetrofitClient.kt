package com.github.tatsuyafujisaki.androidplayground.network

import com.github.tatsuyafujisaki.androidplayground.BuildConfig
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addCookieJar
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addInterceptors
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    fun getGoogleApiService(): GoogleApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptors()
                    .addCookieJar()
                    .build()
            )
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GoogleApiService::class.java)
}
