package com.github.tatsuyafujisaki.androidplayground.di

import com.github.tatsuyafujisaki.androidplayground.dataclass.Sample
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun getSample() = Sample("foo")
}
