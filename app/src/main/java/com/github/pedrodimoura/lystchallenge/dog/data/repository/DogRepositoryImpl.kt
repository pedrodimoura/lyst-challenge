package com.github.pedrodimoura.lystchallenge.dog.data.repository

import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.DogLocalDatasource
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.model.BreedLocalEntity
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.DogRemoteDatasource
import com.github.pedrodimoura.lystchallenge.dog.domain.model.Breed
import com.github.pedrodimoura.lystchallenge.dog.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val dogRemoteDatasource: DogRemoteDatasource,
    private val dogLocalDatasource: DogLocalDatasource,
) : DogRepository {

    override suspend fun fetchBreeds(): Flow<Unit> {
        return flow {
            runCatching {
                dogRemoteDatasource.getBreeds()
                    .collect { breedsResponsePayload ->
                        save(breedsResponsePayload.map {
                            Breed(it.id, it.name, it.image.url, it.temperament.orEmpty())
                        })
                    }
            }.onSuccess { emit(Unit) }
        }
    }

    override suspend fun getBreeds(): Flow<List<Breed>> {
        return dogLocalDatasource.getAll().map { breedsLocalEntity ->
            breedsLocalEntity.map {
                Breed(
                    it.id,
                    it.name,
                    it.imageUrl,
                    it.temperament
                )
            }
        }
    }

    override suspend fun search(breed: String): Flow<List<Breed>> {
        return dogLocalDatasource.search(breed)
            .map { breedsLocalPayload ->
                breedsLocalPayload.map {
                    Breed(it.id, it.name, it.imageUrl, it.temperament)
                }
            }
    }

    override suspend fun save(breeds: List<Breed>) {
        dogLocalDatasource.save(breeds.map {
            BreedLocalEntity(
                it.id,
                it.name,
                it.imageUrl,
                it.temperament
            )
        })
    }

}