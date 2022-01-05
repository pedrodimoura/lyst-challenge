package com.github.pedrodimoura.lystchallenge.dog.presentation.state

import com.github.pedrodimoura.lystchallenge.dog.domain.model.Breed

sealed class BreedsUIState {
    object Ready : BreedsUIState()
    object FetchingBreeds : BreedsUIState()
    data class BreedsFetched(val breeds: List<Breed>) : BreedsUIState()
    data class Failure(val message: String) : BreedsUIState()
}
