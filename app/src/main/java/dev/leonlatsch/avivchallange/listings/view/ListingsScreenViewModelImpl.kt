package dev.leonlatsch.avivchallange.listings.view

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonlatsch.avivchallange.core.Result
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingsScreenViewModelImpl @Inject constructor(
    private val getListings: GetListingsUseCase,
    private val listingsUiStateFactory: ListingsUiStateFactory
) : ListingsScreenViewModel() {

    private val listingsFlow = MutableStateFlow(emptyList<Listing>())
    private val loadingState = MutableStateFlow(LoadingState.NotLoading)
    private val errorState = MutableStateFlow(ErrorState.NoError)

    override val uiState = combine(
        listingsFlow,
        loadingState,
        errorState,
    ) { listings, loadingState, errorState ->
        listingsUiStateFactory.create(listings, loadingState, errorState)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ListingsUiState.Loading)

    override fun handleUiEvent(event: ListingsUiEvent) {
        when (event) {
            is ListingsUiEvent.OpenListing -> TODO()
            is ListingsUiEvent.Refresh -> refresh()
        }
    }

    override fun refresh() {
        viewModelScope.launch {
            loadingState.value = LoadingState.Loading
            errorState.value = ErrorState.NoError

            when (val result = getListings()) {
                is Result.Success -> listingsFlow.value = result.data
                is Result.Error -> {
                    errorState.value = ErrorState.Error
                }
            }

            loadingState.value = LoadingState.NotLoading
        }
    }
}

