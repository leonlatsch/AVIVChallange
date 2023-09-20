package dev.leonlatsch.avivchallange.listings.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingsUseCase
import dev.leonlatsch.avivchallange.listings.view.navigation.ListingsScreenNavigationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingsScreenViewModel @Inject constructor(
    private val getListings: GetListingsUseCase,
    private val listingsUiStateFactory: ListingsUiStateFactory
) : ViewModel() {

    private val listingsFlow = MutableStateFlow(emptyList<Listing>())
    private val loadingState = MutableStateFlow(LoadingState.Loading)
    private val errorState = MutableStateFlow(ErrorState.NoError)

    val uiState = combine(
        listingsFlow,
        loadingState,
        errorState,
    ) { listings, loadingState, errorState ->
        listingsUiStateFactory.create(listings, loadingState, errorState)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ListingsUiState.Loading)

    private val eventChannel = Channel<ListingsScreenNavigationEvent>(Channel.UNLIMITED)
    val eventFlow = eventChannel.receiveAsFlow()

    fun handleUiEvent(event: ListingsUiEvent) {
        when (event) {
            is ListingsUiEvent.OpenListing -> {
                eventChannel.trySend(ListingsScreenNavigationEvent.OpenDetailScreen(event.listingId))
            }
            is ListingsUiEvent.Refresh -> refresh()
        }
    }

    fun refresh() {
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

