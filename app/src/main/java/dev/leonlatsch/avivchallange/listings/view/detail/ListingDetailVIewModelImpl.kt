package dev.leonlatsch.avivchallange.listings.view.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonlatsch.avivchallange.core.Result
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingDetailVIewModelImpl @Inject constructor(
    private val getListingDetail: GetListingDetailUseCase,
    private val listingDetailUiStateFactory: ListingDetailUiStateFactory
): ListingDetailViewModel() {

    private val listingDetailFlow = MutableStateFlow<Listing?>(null)
    private val loadingState = MutableStateFlow(LoadingState.NotLoading)
    private val errorState = MutableStateFlow(ErrorState.NoError)

    override val uiState = combine(
        listingDetailFlow,
        loadingState,
        errorState
    ) { listingDetail, loadingState, errorState ->
        listingDetailUiStateFactory.create(listingDetail, loadingState, errorState)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ListingDetailUiState.Loading)

    override fun refresh() {
        viewModelScope.launch {
            loadingState.value = LoadingState.Loading
            errorState.value = ErrorState.NoError

            when (val result = getListingDetail(0)) { // TODO
                is Result.Success -> listingDetailFlow.value = result.data
                is Result.Error -> {
                    errorState.value = ErrorState.Error
                }
            }

            loadingState.value = LoadingState.NotLoading
        }
    }
}