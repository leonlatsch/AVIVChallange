package dev.leonlatsch.avivchallange.listings.view

sealed interface ListingsUiState {

    data object Loading : ListingsUiState

    data object Error : ListingsUiState

    data class Content(
        val listings: List<ListingCard>,
        val numberOfListings: Int,
        val isRefreshing: Boolean,
        val hasError: Boolean,
    ) : ListingsUiState
}