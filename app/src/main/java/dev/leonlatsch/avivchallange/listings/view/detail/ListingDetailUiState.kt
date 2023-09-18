package dev.leonlatsch.avivchallange.listings.view.detail

import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData

sealed interface ListingDetailUiState {
    data object Loading : ListingDetailUiState
    data object Error : ListingDetailUiState
    data class Content(
        val listingDetail: ListingViewData,
        val isRefreshing: Boolean,
        val hasError: Boolean,
    ) : ListingDetailUiState
}