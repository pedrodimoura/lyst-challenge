package com.github.pedrodimoura.lystchallenge.dog.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pedrodimoura.lystchallenge.common.di.qualifier.DispatcherIO
import com.github.pedrodimoura.lystchallenge.dog.domain.repository.DogRepository
import com.github.pedrodimoura.lystchallenge.dog.presentation.state.BreedsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@FlowPreview
class BreedListViewModel @Inject constructor(
    private val dogRepository: DogRepository,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _breedsUIState: MutableStateFlow<BreedsUIState> =
        MutableStateFlow(BreedsUIState.Ready)

    val breedsUIState: StateFlow<BreedsUIState> = _breedsUIState

    fun getBreeds() {
        viewModelScope.launch {
            dogRepository.fetchBreeds()
                .flowOn(coroutineDispatcher)
                .onStart { _breedsUIState.value = BreedsUIState.FetchingBreeds }
                .flatMapConcat { dogRepository.getBreeds() }
                .catch { _breedsUIState.value = BreedsUIState.Failure(it.message.orEmpty()) }
                .collect {
                    _breedsUIState.value = BreedsUIState.BreedsFetched(it)
                    _breedsUIState.value = BreedsUIState.Ready
                }
        }
    }

}