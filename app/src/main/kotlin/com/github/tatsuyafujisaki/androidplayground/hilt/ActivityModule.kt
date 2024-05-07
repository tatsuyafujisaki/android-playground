package com.github.tatsuyafujisaki.androidplayground.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.serialization.json.Json

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}
