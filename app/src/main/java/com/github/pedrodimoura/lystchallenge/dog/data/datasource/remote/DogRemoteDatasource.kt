package com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote

import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.model.BreedResponsePayload
import kotlinx.coroutines.flow.Flow

interface DogRemoteDatasource {
    suspend fun getBreeds(): Flow<List<BreedResponsePayload>>
}
