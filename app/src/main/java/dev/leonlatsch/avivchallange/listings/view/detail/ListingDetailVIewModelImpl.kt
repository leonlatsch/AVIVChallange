package dev.leonlatsch.avivchallange.listings.view.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.usecase.ObserveListingDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListingDetailVIewModelImpl @Inject constructor(
    private val observeListingDetailUseCase: ObserveListingDetailUseCase,
): ListingDetailViewModel() {

    private val loadingState = MutableStateFlow(LoadingState.NotLoading)
    private val errorState = MutableStateFlow(ErrorState.NoError)

    override val uiState: StateFlow<ListingDetailUiState> = combine(
        observeListingDetailUseCase(TODO("listing id")),
        loadingState,
        errorState
    ) { listingDetail, loadingState, errorState ->
        ListingDetailUiState.Loading // TODO: Create ui state
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ListingDetailUiState.Loading)

}