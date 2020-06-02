package com.github.tatsuyafujisaki.androidplayground.di

import android.content.Context
import com.github.tatsuyafujisaki.androidplayground.MainApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideContext(application: MainApplication): Context = application.applicationContext
}
