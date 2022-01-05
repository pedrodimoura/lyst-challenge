package com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.impl

import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.DogLocalDatasource
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.dao.DogDao
import com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.model.BreedLocalEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogLocalDatasourceImpl @Inject constructor(
    private val dogDao: DogDao,
) : DogLocalDatasource {

    override suspend fun save(breeds: List<BreedLocalEntity>) = dogDao.save(breeds)

    override suspend fun search(breed: String): Flow<List<BreedLocalEntity>> = dogDao.search(breed)

    override suspend fun getAll(): Flow<List<BreedLocalEntity>> = dogDao.getAll()
}
