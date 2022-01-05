package com.github.pedrodimoura.lystchallenge.dog.data.datasource.local

import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.model.BreedLocalEntity
import kotlinx.coroutines.flow.Flow

interface DogLocalDatasource {
    suspend fun save(breeds: List<BreedLocalEntity>)
    suspend fun search(breed: String): Flow<List<BreedLocalEntity>>
    suspend fun getAll(): Flow<List<BreedLocalEntity>>
}