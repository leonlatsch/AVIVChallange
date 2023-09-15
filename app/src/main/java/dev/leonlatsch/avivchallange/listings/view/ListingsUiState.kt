package dev.leonlatsch.avivchallange.listings.view

import dev.leonlatsch.avivchallange.listings.domain.model.Listing

sealed interface ListingsUiState {

    data object Loading : ListingsUiState

    data object Error : ListingsUiState

    data class Content(
        val listings: List<Listing>,
        val numberOfListings: Int,
    ) : ListingsUiState
}