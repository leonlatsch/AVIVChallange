package dev.leonlatsch.avivchallange.listings.view.navigation

sealed interface ListingsScreenNavigationEvent {
    data class OpenDetailScreen(val listingId: Int) : ListingsScreenNavigationEvent
}