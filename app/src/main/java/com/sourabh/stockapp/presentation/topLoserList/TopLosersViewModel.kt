package com.sourabh.stockapp.presentation.topLoserList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.stockapp.domain.repository.StockRepository
import com.sourabh.stockapp.presentation.topGainerList.TopGainerEvent
import com.sourabh.stockapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopLosersViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    var state by mutableStateOf(TopLoserState())

    private var searchJob: Job? = null

    init {
        getTopLoserListings()
    }

    fun onEvent(event: TopLoserEvent) {
        when (event) {
//            is TopGainerEvent.Refresh -> {
//                getTopLoserListings(fetchFromRemote = true)
//            }

            is TopLoserEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getTopLoserListings()
                }
            }
        }
    }

    private fun getTopLoserListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getTopLoserList(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings
                                )
                            }
                        }

                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}