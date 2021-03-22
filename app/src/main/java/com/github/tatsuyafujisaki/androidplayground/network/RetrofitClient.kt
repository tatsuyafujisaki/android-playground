package com.github.tatsuyafujisaki.androidplayground.network

import com.github.tatsuyafujisaki.androidplayground.BuildConfig
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addCookieJar
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addInterceptors
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private val okHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptors()
            .addCookieJar()
            .build()

    val googleApiService: GoogleApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(GoogleApiService::class.java)
}
