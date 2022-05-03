package com.weatherapp.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.search.databinding.ItemResultBinding

class SearchResultsListViewHolder(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup,
    private val binding: ItemResultBinding = ItemResultBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Search, onResultItemClick: (Int) -> Unit) {
        binding.apply {
            textViewTitle.text = result.title
            textViewLocation.text = result.locationType
            itemResultContainer.setOnClickListener {
                onResultItemClick.invoke(result.woeid)
            }
        }
    }
}