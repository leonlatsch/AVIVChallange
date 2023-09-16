package dev.leonlatsch.avivchallange.listings.view

sealed interface ListingsUiEvent {
    data object Refresh : ListingsUiEvent
    data class OpenListing(val listingId: Int) : ListingsUiEvent
}