package dev.leonlatsch.avivchallange.listings.view.list

import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData

sealed interface ListingsUiState {

    data object Loading : ListingsUiState

    data object Error : ListingsUiState

    data class Content(
        val listings: List<ListingViewData>,
        val numberOfListings: Int,
        val isRefreshing: Boolean,
        val hasError: Boolean,
    ) : ListingsUiState
}