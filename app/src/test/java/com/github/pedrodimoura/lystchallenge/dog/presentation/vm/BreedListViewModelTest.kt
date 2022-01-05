package com.github.pedrodimoura.lystchallenge.dog.presentation.vm

import app.cash.turbine.test
import com.github.pedrodimoura.lystchallenge.dog.domain.repository.DogRepository
import com.github.pedrodimoura.lystchallenge.dog.presentation.state.BreedsUIState
import com.github.pedrodimoura.lystchallenge.util.TestCoroutineRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class BreedListViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val dogRepository: DogRepository by lazy { mockk() }

    private val viewModel: BreedListViewModel by lazy {
        BreedListViewModel(dogRepository, testCoroutineRule.testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `StateFlow SHOULD emit Ready State WHEN getBreeds is not invoked`() = runBlockingTest {
        viewModel.breedsUIState.test {
            assertEquals(BreedsUIState.Ready, awaitItem())
        }
    }

    @Test
    fun `StateFlow SHOULD emit Ready, FetchingBreeds, BreedsFetched and Ready WHEN getBreeds is invoked`() =
        runBlockingTest {
            coEvery { dogRepository.fetchBreeds() } returns flowOf(Unit)
            coEvery { dogRepository.getBreeds() } returns flowOf(emptyList())

            viewModel.breedsUIState.test {
                viewModel.getBreeds()
                assertEquals(BreedsUIState.Ready, awaitItem())
                assertEquals(BreedsUIState.FetchingBreeds, awaitItem())
                assertEquals(BreedsUIState.BreedsFetched(emptyList()), awaitItem())
                assertEquals(BreedsUIState.Ready, awaitItem())
            }
        }

    @Test
    fun `StateFlow SHOULD emit Ready, FetchingBreeds and Failure WHEN fetchBreed fail`() =
        runBlockingTest {
            coEvery {
                dogRepository.fetchBreeds()
            } returns flow { throw NullPointerException("message") } // Random exception only for testing purpose

            viewModel.breedsUIState.test {
                viewModel.getBreeds()
                assertEquals(BreedsUIState.Ready, awaitItem())
                assertEquals(BreedsUIState.FetchingBreeds, awaitItem())
                assertEquals(BreedsUIState.Failure("message"), awaitItem())
            }
        }

    @Test
    fun `StateFlow SHOULD emit Ready, FetchingBreeds and Failure WHEN getBreeds fail`() =
        runBlockingTest {
            coEvery { dogRepository.fetchBreeds() } returns flowOf(Unit)
            coEvery {
                dogRepository.getBreeds()
            } returns flow { throw NullPointerException("message") } // Random exception only for testing purpose

            viewModel.breedsUIState.test {
                viewModel.getBreeds()
                assertEquals(BreedsUIState.Ready, awaitItem())
                assertEquals(BreedsUIState.FetchingBreeds, awaitItem())
                assertEquals(BreedsUIState.Failure("message"), awaitItem())
            }
        }

}
