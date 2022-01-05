package com.github.pedrodimoura.lystchallenge.dog.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.pedrodimoura.lystchallenge.databinding.ItemBreedBinding
import com.github.pedrodimoura.lystchallenge.dog.domain.model.Breed

class BreedViewHolder(
    private val viewBinding: ItemBreedBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(breed: Breed) {
        with(viewBinding) {
            tvName.text = breed.name
            tvTemperament.text = breed.temperament
        }
    }
}
