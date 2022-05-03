package com.weatherapp.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.search.databinding.ActivitySearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private var binding: ActivitySearchBinding? = null
    private val searchViewModel: SearchViewModel by viewModel()
    private var searchResultsListAdapter = SearchResultsListAdapter()

    private val watcher = object : TextWatcher {
        private var searchFor = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return

            searchFor = searchText

            launch {
                delay(300)  //debounce timeOut
                if (searchText != searchFor)
                    return@launch

                searchViewModel.getResults(searchText)
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpRecyclerView()
        addListeners()
        searchResultsObserver()
    }

    private fun addListeners() {
        binding?.editTextSearchWeather?.addTextChangedListener(watcher)
    }

    private fun setUpRecyclerView() {
        binding?.searchResult?.apply {
            adapter = searchResultsListAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    private fun searchResultsObserver() {
        searchViewModel.viewState.observe(this, ::handleUiState)
    }

    private fun handleUiState(state: SearchUiState) {
        when (state) {
            is SearchUiState.ListCategories -> setSearchData(state.data)
        }
    }

    private fun setSearchData(result: List<Search>) {
        searchResultsListAdapter.setResultList(result)
        searchResultsListAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}