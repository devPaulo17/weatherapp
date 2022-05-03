package com.weatherapp.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.domain.entities.search.Search

class SearchResultsListAdapter: RecyclerView.Adapter<SearchResultsListViewHolder>()  {

    var resultsList = listOf<Search>()
        private set

    fun setResultList(value: List<Search>) {
        resultsList = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchResultsListViewHolder(layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: SearchResultsListViewHolder, position: Int) {
        val item = resultsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = resultsList.size

}