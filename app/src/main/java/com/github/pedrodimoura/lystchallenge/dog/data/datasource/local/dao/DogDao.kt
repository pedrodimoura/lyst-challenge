package com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.model.BreedLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Insert(entity = BreedLocalEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(breeds: List<BreedLocalEntity>)

    @Query("SELECT * FROM breedlocalentity WHERE name = :breedName")
    fun search(breedName: String): Flow<List<BreedLocalEntity>>

    @Query("SELECT * FROM breedlocalentity")
    fun getAll(): Flow<List<BreedLocalEntity>>

}
