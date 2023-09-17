package dev.leonlatsch.avivchallange.listings.view.detail.compose

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailViewModel

const val LISTING_DETAIL_SCREEN_NAV_PATH = "/listings/{listingId}"

@Composable
fun ListingDetailScreen() {
    val viewModel = hiltViewModel<ListingDetailViewModel>()
}