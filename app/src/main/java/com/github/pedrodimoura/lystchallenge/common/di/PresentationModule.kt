package com.github.pedrodimoura.lystchallenge.common.di

import com.github.pedrodimoura.lystchallenge.common.di.qualifier.DispatcherIO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @DispatcherIO
    @Provides
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

}