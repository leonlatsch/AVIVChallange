package dev.leonlatsch.avivchallange.listings.view.detail

sealed interface ListingDetailUiEvent {
    data object Refresh : ListingDetailUiEvent
}