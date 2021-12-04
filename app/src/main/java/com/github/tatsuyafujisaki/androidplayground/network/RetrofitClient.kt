package com.github.tatsuyafujisaki.androidplayground.network

import android.content.Context
import com.github.tatsuyafujisaki.androidplayground.BuildConfig
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addCookieJar
import com.github.tatsuyafujisaki.androidplayground.util.OkHttpUtil.addInterceptors
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    fun getGoogleApiService(context: Context): GoogleApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptors(context)
                    .addCookieJar()
                    .build()
            )
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(GoogleApiService::class.java)
}
