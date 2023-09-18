package dev.leonlatsch.avivchallange.listings.view.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonlatsch.avivchallange.core.Result
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingDetailUseCase
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ARG_LISTING_DETAIL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.IllegalArgumentException

@HiltViewModel
class ListingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getListingDetail: GetListingDetailUseCase,
    private val listingDetailUiStateFactory: ListingDetailUiStateFactory,
): ViewModel() {

    private val listingId = savedStateHandle.get<Int>(ARG_LISTING_DETAIL) ?: throw IllegalArgumentException("Argument $ARG_LISTING_DETAIL not passed")

    private val listingDetailFlow = MutableStateFlow<Listing?>(null)
    private val loadingState = MutableStateFlow(LoadingState.NotLoading)
    private val errorState = MutableStateFlow(ErrorState.NoError)

    val uiState = combine(
        listingDetailFlow,
        loadingState,
        errorState
    ) { listingDetail, loadingState, errorState ->
        listingDetailUiStateFactory.create(listingDetail, loadingState, errorState)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ListingDetailUiState.Loading)

    fun refresh() {
        viewModelScope.launch {
            loadingState.value = LoadingState.Loading
            errorState.value = ErrorState.NoError

            when (val result = getListingDetail(listingId)) {
                is Result.Success -> listingDetailFlow.value = result.data
                is Result.Error -> {
                    errorState.value = ErrorState.Error
                }
            }

            loadingState.value = LoadingState.NotLoading
        }
    }
}