package com.github.tatsuyafujisaki.androidplayground

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: MainApplication): Context = application.applicationContext
}