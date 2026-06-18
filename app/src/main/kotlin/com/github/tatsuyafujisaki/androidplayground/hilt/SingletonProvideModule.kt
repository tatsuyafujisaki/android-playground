package com.github.tatsuyafujisaki.androidplayground.hilt

import com.github.tatsuyafujisaki.androidplayground.network.JsonPlaceholderService
import com.github.tatsuyafujisaki.androidplayground.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object SingletonProvideModule {
    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    fun provideJsonPlaceholderService(): JsonPlaceholderService =
        RetrofitClient.createJsonPlaceholderService()
}
