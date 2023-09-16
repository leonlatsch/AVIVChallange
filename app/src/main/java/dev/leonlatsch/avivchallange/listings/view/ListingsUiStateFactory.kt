package dev.leonlatsch.avivchallange.listings.view

import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

class ListingsUiStateFactory @Inject constructor() {

    fun create(listings: List<Listing>, loadingState: LoadingState, errorState: ErrorState): ListingsUiState {
        return when {
            listings.isNotEmpty() -> {
                ListingsUiState.Content(
                    listings = listings.map { mapListingToUiModel(it) },
                    numberOfListings = listings.size,
                    isRefreshing = loadingState == LoadingState.Loading,
                    hasError = errorState == ErrorState.Error,
                )
            }
            loadingState == LoadingState.Loading -> ListingsUiState.Loading
            errorState == ErrorState.Error ->  ListingsUiState.Error
            else -> ListingsUiState.Error
        }
    }

    // TODO: formatting
    private fun mapListingToUiModel(listing: Listing): ListingCard = with(listing) {
        val currencyFormat = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
            currency = Currency.getInstance("EUR")
        }

        ListingCard(
            id = id,
            bedrooms = bedrooms?.let { "$it bedrooms" },
            city = city,
            area = "$area M²",
            url = url,
            price = currencyFormat.format(price),
            professional = professional,
            propertyType = propertyType,
            rooms = "$rooms total rooms"
        )
    }
}