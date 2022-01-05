package com.github.pedrodimoura.lystchallenge.common.di

import android.content.Context
import androidx.room.Room
import com.github.pedrodimoura.lystchallenge.app.data.LystDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDependencyModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ): LystDatabase {
        return Room
            .inMemoryDatabaseBuilder(context, LystDatabase::class.java)
            .build()
    }

}