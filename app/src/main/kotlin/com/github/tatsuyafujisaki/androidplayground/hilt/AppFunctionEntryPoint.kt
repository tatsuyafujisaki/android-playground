package com.github.tatsuyafujisaki.androidplayground.hilt

import com.github.tatsuyafujisaki.androidplayground.network.JsonPlaceholderService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppFunctionEntryPoint {
    fun jsonPlaceholderService(): JsonPlaceholderService
}
