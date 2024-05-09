package com.github.tatsuyafujisaki.androidplayground.hilt

import com.github.tatsuyafujisaki.androidplayground.domain.MyRealmRepository
import com.github.tatsuyafujisaki.androidplayground.domain.MyRealmRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonBindModule {
    @Binds
    abstract fun bindMyRealmRepository(
        myRealmRepository: MyRealmRepositoryImpl
    ): MyRealmRepository
}
