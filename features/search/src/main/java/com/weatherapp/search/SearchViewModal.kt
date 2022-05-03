package com.weatherapp.search

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.weatherapp.domain.entities.search.Search
import com.weatherapp.search.databinding.ActivitySearchBinding
import com.weatherapp.weather.WeatherDetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

const val WOEID_LOCATION = "woeidLocation"

class SearchViewModal : BottomSheetDialogFragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private var binding: ActivitySearchBinding? = null

    private val searchViewModel: SearchViewModel by viewModel()

    private val onResultItemClick: (Int) -> Unit = { woeid ->
        goToLocationWeatherDetail(woeid)
    }

    private var searchResultsListAdapter = SearchResultsListAdapter(onResultItemClick)

    private val watcher = object : TextWatcher {
        private var searchFor = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return

            searchFor = searchText

            launch {
                delay(500)  //debounce timeOut
                if (searchText != searchFor)
                    return@launch

                binding?.progressBarSearch?.visibility = View.VISIBLE
                binding?.clearText?.visibility = View.GONE
                searchViewModel.getResults(searchText)
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        addListeners()
        searchResultsObserver()
        setListeners()
    }

    private fun setListeners() {
        binding?.apply {
            clearText.setOnClickListener {
                setErrorState()
            }
            closeModal.setOnClickListener { dialog?.dismiss() }
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.setFullHeight()
    }


    private fun addListeners() {
        binding?.editTextSearchWeather?.addTextChangedListener(watcher)
    }

    private fun setUpRecyclerView() {
        binding?.searchResult?.apply {
            adapter = searchResultsListAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun searchResultsObserver() {
        searchViewModel.viewState.observe(this, ::handleUiState)
    }

    private fun handleUiState(state: SearchUiState) {
        when (state) {
            is SearchUiState.SearchResultList -> setSearchData(state.data)
            is SearchUiState.Loading -> showLoadingState()
            is SearchUiState.Error -> setErrorState()
            else -> {
                setErrorState()
            }
        }
    }

    private fun showLoadingState() {
        binding?.progressBarSearch?.visibility = View.VISIBLE
        binding?.clearText?.visibility = View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setSearchData(result: List<Search>) {
        binding?.apply {
            if (result.isEmpty()) {
                imageEmptyState.visibility = View.VISIBLE
                progressBarSearch.visibility = View.GONE
                emptyStateLabel.visibility = View.VISIBLE
                searchResult.visibility = View.GONE
                clearText.visibility = View.VISIBLE

            } else {
                imageEmptyState.visibility = View.GONE
                emptyStateLabel.visibility = View.GONE
                searchResult.visibility = View.VISIBLE
                clearText.visibility = View.VISIBLE
                progressBarSearch.visibility = View.GONE
                searchResultsListAdapter.setResultList(result)
                searchResultsListAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setErrorState() {
        binding?.apply {
            searchResult.visibility = View.GONE
            clearText.visibility = View.GONE
            emptyStateLabel.visibility = View.GONE
            progressBarSearch.visibility = View.GONE
            editTextSearchWeather.text.clear()
            imageEmptyState.visibility = View.VISIBLE
        }
    }

    private fun goToLocationWeatherDetail(woeid: Int) {
        startActivity(Intent(activity, WeatherDetailActivity::class.java).apply {
            putExtra(WOEID_LOCATION, woeid)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun Dialog.setFullHeight() {
        this.also {
            it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.apply {
                layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
                BottomSheetBehavior.from(this).run {
                    peekHeight = resources.displayMetrics.heightPixels
                    isHideable = false
                }
                this.requestLayout()
            }
        }
    }
}