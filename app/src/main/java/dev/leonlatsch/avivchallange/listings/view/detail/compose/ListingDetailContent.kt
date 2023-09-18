package dev.leonlatsch.avivchallange.listings.view.detail.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailUiState

@Composable
fun ListingDetailContent(uiState: ListingDetailUiState.Content) {
    Text(text = uiState.listingDetail.price)
}