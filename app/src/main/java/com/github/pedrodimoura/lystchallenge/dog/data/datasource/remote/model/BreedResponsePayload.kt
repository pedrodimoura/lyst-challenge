package com.github.pedrodimoura.lystchallenge.dog.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedResponsePayload(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("temperament")
    val temperament: String? = null,
    @SerialName("image")
    val image: Image,
)
