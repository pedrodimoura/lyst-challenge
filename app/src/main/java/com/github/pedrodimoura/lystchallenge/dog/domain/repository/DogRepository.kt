package com.github.pedrodimoura.lystchallenge.dog.domain.repository

import com.github.pedrodimoura.lystchallenge.dog.domain.model.Breed
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun fetchBreeds(): Flow<Unit>
    suspend fun getBreeds(): Flow<List<Breed>>
    suspend fun search(breed: String): Flow<List<Breed>>
    suspend fun save(breeds: List<Breed>)
}
