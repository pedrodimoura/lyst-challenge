package com.github.pedrodimoura.lystchallenge.dog.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedLocalEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val imageUrl: String,
    val temperament: String,
)
