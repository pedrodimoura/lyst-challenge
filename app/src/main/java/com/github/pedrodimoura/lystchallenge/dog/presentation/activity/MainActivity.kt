package com.github.pedrodimoura.lystchallenge.dog.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pedrodimoura.lystchallenge.databinding.ActivityMainBinding
import com.github.pedrodimoura.lystchallenge.dog.presentation.adapter.BreedAdapter
import com.github.pedrodimoura.lystchallenge.dog.presentation.state.BreedsUIState
import com.github.pedrodimoura.lystchallenge.dog.presentation.vm.BreedListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

private const val LOG_TAG = "LOG_TAG"

@AndroidEntryPoint
@FlowPreview
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val breedAdapter: BreedAdapter by lazy { BreedAdapter() }
    private val viewModel: BreedListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBreeds.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = breedAdapter
        }

        viewModel.breedsUIState.asLiveData().observe(this) { state ->
            when (state) {
                is BreedsUIState.Ready -> log("ready")
                is BreedsUIState.FetchingBreeds -> log("fetching breeds")
                is BreedsUIState.BreedsFetched -> {
                    log("breeds fetched, size ${state.breeds.size}")
                    breedAdapter.submitList(state.breeds)
                }
                is BreedsUIState.Failure -> log("failure, message: ${state.message}")
            }
        }

        viewModel.getBreeds()
    }

    private fun log(message: String) = Log.d(LOG_TAG, message)
}