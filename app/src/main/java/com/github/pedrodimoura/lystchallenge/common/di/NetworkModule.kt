package com.github.pedrodimoura.lystchallenge.common.di

import com.github.pedrodimoura.lystchallenge.common.data.remote.HttpClientProvider
import com.github.pedrodimoura.lystchallenge.common.data.remote.ktor.KtorClientImpl
import com.github.pedrodimoura.lystchallenge.common.di.qualifier.KtorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @KtorClient
    @Singleton
    @Provides
    fun providesClient(): HttpClientProvider.KtorClient = KtorClientImpl()
}
