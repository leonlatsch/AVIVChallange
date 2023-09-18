package dev.leonlatsch.avivchallange.listings.view.detail.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailUiEvent

@Composable
fun ListingDetailError(handleUiEvent: (ListingDetailUiEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Error loading Listing detail",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            TextButton(onClick = { handleUiEvent(ListingDetailUiEvent.Refresh) }) {
                Text(text = "Retry")
            }
        }
    }
}