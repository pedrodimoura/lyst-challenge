package com.github.pedrodimoura.lystchallenge.dog.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.pedrodimoura.lystchallenge.databinding.ItemBreedBinding
import com.github.pedrodimoura.lystchallenge.dog.domain.model.Breed

class BreedAdapter : ListAdapter<Breed, BreedViewHolder>(BreedDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding = ItemBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) =
        holder.bind(getItem(position))
}
