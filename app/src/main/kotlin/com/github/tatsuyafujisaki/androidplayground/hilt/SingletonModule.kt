package com.github.tatsuyafujisaki.androidplayground.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}
