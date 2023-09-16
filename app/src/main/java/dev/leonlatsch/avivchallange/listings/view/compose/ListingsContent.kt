package dev.leonlatsch.avivchallange.listings.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.view.ListingsUiState

@Composable
fun ListingsContent(uiState: ListingsUiState.Content) {

}

@Preview
@Composable
private fun ListingsContentPreview() {
    ListingsContent(
        ListingsUiState.Content(
            listings = listOf(
                Listing(
                    id = 1,
                    bedrooms = 4,
                    city = "Hamburg",
                    area = 200f,
                    url = "",
                    price = 700f,
                    professional = "",
                    propertyType = "type",
                    offerType = 1,
                    rooms = 5,
                )
            ),
            numberOfListings = 1
        )
    )
}