package com.github.pedrodimoura.lystchallenge.dog.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.pedrodimoura.lystchallenge.dog.domain.model.Breed

class BreedDiff : DiffUtil.ItemCallback<Breed>() {
    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean =
        oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean =
        oldItem == newItem
}