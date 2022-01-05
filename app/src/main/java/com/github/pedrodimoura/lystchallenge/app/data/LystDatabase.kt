package com.github.pedrodimoura.lystchallenge.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.dao.DogDao
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.model.BreedLocalEntity

@Database(entities = [BreedLocalEntity::class], version = 1)
abstract class LystDatabase : RoomDatabase() {
    abstract fun getDogDao(): DogDao
}