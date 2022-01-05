package com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.service

import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.model.BreedResponsePayload

interface DogServiceApi {
    suspend fun getBreeds(): List<BreedResponsePayload>
}