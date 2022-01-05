package com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.service.impl

import com.github.pedrodimoura.lystchallenge.BuildConfig
import com.github.pedrodimoura.lystchallenge.common.data.remote.HttpClientProvider
import com.github.pedrodimoura.lystchallenge.common.di.qualifier.KtorClient
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.model.BreedResponsePayload
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.service.DogServiceApi
import io.ktor.client.request.*
import javax.inject.Inject

private const val BREEDS_ENDPOINT = "${BuildConfig.BASE_URL}breeds"

class DogServiceApiImpl @Inject constructor(
    @KtorClient private val ktorClient: HttpClientProvider.KtorClient,
) : DogServiceApi {

    override suspend fun getBreeds(): List<BreedResponsePayload> =
        ktorClient.getClient().get(BREEDS_ENDPOINT)
}
