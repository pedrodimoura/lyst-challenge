package com.github.pedrodimoura.lystchallenge.dog.di

import com.github.pedrodimoura.lystchallenge.app.data.LystDatabase
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.DogLocalDatasource
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.dao.DogDao
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.impl.DogLocalDatasourceImpl
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.DogRemoteDatasource
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.impl.DogRemoteDatasourceImpl
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.service.DogServiceApi
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.service.impl.DogServiceApiImpl
import com.github.pedrodimoura.lystchallenge.dog.data.repository.DogRepositoryImpl
import com.github.pedrodimoura.lystchallenge.dog.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryDependencyModule {
    @Binds
    abstract fun bindsDogServiceApi(
        dogServiceApiImpl: DogServiceApiImpl
    ): DogServiceApi

    @Binds
    abstract fun bindsDogRemoteDatasource(
        dogRemoteDatasourceImpl: DogRemoteDatasourceImpl,
    ): DogRemoteDatasource

    @Binds
    abstract fun bindsDogLocalDatasource(
        dogLocalDatasourceImpl: DogLocalDatasourceImpl,
    ): DogLocalDatasource

    @Binds
    abstract fun bindsDogRepository(
        dogRepositoryImpl: DogRepositoryImpl,
    ): DogRepository
}

@Module
@InstallIn(SingletonComponent::class)
object LocalDependencyModule {
    @Provides
    @Singleton
    fun providesDogsDao(
        lystDatabase: LystDatabase
    ): DogDao = lystDatabase.getDogDao()
}