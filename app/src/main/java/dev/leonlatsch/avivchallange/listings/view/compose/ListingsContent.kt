package dev.leonlatsch.avivchallange.listings.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.leonlatsch.avivchallange.R
import dev.leonlatsch.avivchallange.listings.view.ListingCard
import dev.leonlatsch.avivchallange.listings.view.ListingsUiEvent
import dev.leonlatsch.avivchallange.listings.view.ListingsUiState
import dev.leonlatsch.avivchallange.theming.theme.AVIVChallangeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListingsContent(
    uiState: ListingsUiState.Content,
    handleUiEvent: (ListingsUiEvent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = { handleUiEvent(ListingsUiEvent.Refresh) }
    )

    LaunchedEffect(uiState.hasError) {
        if (uiState.hasError) {
            snackbarHostState.showSnackbar("Error refreshing listings")
        }
    }

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.padding(12.dp)
        ) {
            items(uiState.listings, key = { it.id }) { listing ->
                ListingItem(
                    listing = listing,
                    onClick = { handleUiEvent(ListingsUiEvent.OpenListing(listing.id)) },
                )
            }
        }

        PullRefreshIndicator(uiState.isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun ListingItem(
    listing: ListingCard,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onClick() }
    ) {
        Card {
            Box {
                if (LocalInspectionMode.current) {
                    Image(
                        painter = painterResource(R.mipmap.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    )
                } else {
                    AsyncImage(
                        model = listing.url,
                        contentDescription = null,
                        fallback = painterResource(R.mipmap.placeholder),
                        contentScale = ContentScale.FillBounds,
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

        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = listing.price)
                Text(text = listing.area)
                if (listing.bedrooms != null) {
                    Text(text = listing.bedrooms)
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(painter = painterResource(R.drawable.ic_house), contentDescription = null)
                Text(text = listing.propertyType)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(painter = painterResource(R.drawable.ic_location), contentDescription = null)
                Text(text = listing.city)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListingsContentPreview() {
    AVIVChallangeTheme {
        ListingsContent(
            uiState = ListingsUiState.Content(
                listings = listOf(
                    ListingCard(
                        id = 1,
                        bedrooms = "4 Bedrooms",
                        city = "Hamburg",
                        area = "200 M2",
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = "150.000 €",
                        professional = "GSL EXPLORE",
                        propertyType = "Maison - Villa",
                        rooms = "8 Rooms Total",
                    ),
                    ListingCard(
                        id = 2,
                        bedrooms = "4 Bedrooms",
                        city = "Hamburg",
                        area = "200 M2",
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = "150.000 €",
                        professional = "GSL EXPLORE",
                        propertyType = "Maison - Villa",
                        rooms = "8 Rooms Total",
                    ),
                ),
                numberOfListings = 1,
                isRefreshing = false,
                hasError = false,
            ),
            handleUiEvent = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}