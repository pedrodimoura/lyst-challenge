package com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.impl

import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.DogRemoteDatasource
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.model.BreedResponsePayload
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.service.DogServiceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogRemoteDatasourceImpl @Inject constructor(
    private val dogServiceApi: DogServiceApi,
) : DogRemoteDatasource {

    override suspend fun getBreeds(): Flow<List<BreedResponsePayload>> =
        flow { emit(dogServiceApi.getBreeds()) }

}