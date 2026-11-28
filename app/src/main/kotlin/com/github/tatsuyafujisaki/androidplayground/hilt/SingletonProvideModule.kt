package com.github.tatsuyafujisaki.androidplayground.hilt

import com.github.tatsuyafujisaki.androidplayground.network.JsonPlaceholderService
import com.github.tatsuyafujisaki.androidplayground.network.createJsonPlaceholderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object SingletonProvideModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideJsonPlaceholderService(json: Json): JsonPlaceholderService =
        createJsonPlaceholderService(json)
}
