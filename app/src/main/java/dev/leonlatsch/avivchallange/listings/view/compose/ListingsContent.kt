package dev.leonlatsch.avivchallange.listings.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.leonlatsch.avivchallange.R
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.view.ListingsUiState
import dev.leonlatsch.avivchallange.theming.theme.AVIVChallangeTheme

@Composable
fun ListingsContent(uiState: ListingsUiState.Content) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(uiState.listings, key = { it.id }) { listing ->
            ListingCard(
                listing = listing,
                onClick = { TODO() }
            )
        }
    }
}

@Composable
fun ListingCard(listing: Listing, onClick: () -> Unit) {
    Column {
        Card {
            Box {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = painterResource(R.mipmap.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    )
                } else {
                    AsyncImage(
                        model = listing.url,
                        contentDescription = null,
                        fallback = painterResource(R.mipmap.placeholder),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    )
                }

                Text(
                    text = listing.professional,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                )
            }
        }

        Row {
            Text(text = listing.price.toString())
            Text(text = listing.area.toString())
            Text(text = listing.bedrooms.toString())
        }

        Text(text = listing.propertyType)
        Text(text = listing.city)
    }
}

@Preview(showBackground = true)
@Composable
private fun ListingsContentPreview() {
    AVIVChallangeTheme {
        ListingsContent(
            ListingsUiState.Content(
                listings = listOf(
                    Listing(
                        id = 1,
                        bedrooms = 4,
                        city = "Hamburg",
                        area = 200f,
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = 1500000.0f,
                        professional = "GSL EXPLORE",
                        propertyType = "Maison - Villa",
                        offerType = 1,
                        rooms = 8,
                    ),
                    Listing(
                        id = 2,
                        bedrooms = 4,
                        city = "Hamburg",
                        area = 200f,
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = 1500000.0f,
                        professional = "GSL EXPLORE",
                        propertyType = "Maison - Villa",
                        offerType = 1,
                        rooms = 8,
                    )
                ),
                numberOfListings = 1
            )
        )
    }
}