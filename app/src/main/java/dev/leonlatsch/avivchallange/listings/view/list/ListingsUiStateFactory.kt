package dev.leonlatsch.avivchallange.listings.view.list

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.view.ListingDomainToUiMapper
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import javax.inject.Inject

class ListingsUiStateFactory @Inject constructor(
    private val listingDomainToUiMapper: Mapper<Listing, ListingViewData>,
) {

    fun create(listings: List<Listing>, loadingState: LoadingState, errorState: ErrorState): ListingsUiState {
        return when {
            listings.isNotEmpty() -> {
                ListingsUiState.Content(
                    listings = listings.map { listingDomainToUiMapper.map(it) },
                    numberOfListings = listings.size,
                    isRefreshing = loadingState == LoadingState.Loading,
                    hasError = errorState == ErrorState.Error,
                )
            }
            loadingState == LoadingState.Loading -> ListingsUiState.Loading
            else -> ListingsUiState.Error
        }
    }
}

