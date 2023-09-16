package dev.leonlatsch.avivchallange.listings.view

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonlatsch.avivchallange.core.Result
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.usecase.LoadListingsUseCase
import dev.leonlatsch.avivchallange.listings.domain.usecase.ObserveListingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListingsScreenViewModelImpl @Inject constructor(
    observeListings: ObserveListingsUseCase,
    private val loadListings: LoadListingsUseCase,
    private val listingsUiStateFactory: ListingsUiStateFactory
) : ListingsScreenViewModel() {

    private val loadingState = MutableStateFlow(LoadingState.NotLoading)
    private val errorState = MutableStateFlow(ErrorState.NoError)

    override val uiState: StateFlow<ListingsUiState> = combine(
        observeListings(),
        loadingState,
        errorState
    ) { listings, loadingState, errorState ->
        listingsUiStateFactory.create(listings, loadingState, errorState)
    }
        .onStart { refresh() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, ListingsUiState.Loading)

    private suspend fun refresh() {
        loadingState.value = LoadingState.Loading

        when (loadListings()) {
            is Result.Success -> {
                loadingState.value = LoadingState.NotLoading
                errorState.value = ErrorState.NoError
            }
            is Result.Error -> {
                loadingState.value = LoadingState.NotLoading
                errorState.value = ErrorState.Error
            }
        }
    }
}

