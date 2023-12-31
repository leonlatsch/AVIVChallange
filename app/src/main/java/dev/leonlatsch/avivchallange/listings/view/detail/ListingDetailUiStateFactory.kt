package dev.leonlatsch.avivchallange.listings.view.detail

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.view.ListingDomainToUiMapper
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import javax.inject.Inject

class ListingDetailUiStateFactory @Inject constructor(
    private val listingDomainToUiMapper: Mapper<Listing, ListingViewData>,
) {
    fun create(listingDetail: Listing?, loadingState: LoadingState, errorState: ErrorState): ListingDetailUiState =
        when {
            listingDetail != null -> ListingDetailUiState.Content(
                listingDetail = listingDomainToUiMapper.map(listingDetail),
                isRefreshing = loadingState == LoadingState.Loading,
                hasError = errorState == ErrorState.Error,
            )
            loadingState == LoadingState.Loading -> ListingDetailUiState.Loading
            else -> ListingDetailUiState.Error
        }
}