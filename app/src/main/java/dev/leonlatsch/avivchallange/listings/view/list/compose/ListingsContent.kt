package dev.leonlatsch.avivchallange.listings.view.list.compose

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.leonlatsch.avivchallange.R
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import dev.leonlatsch.avivchallange.listings.view.list.ListingsUiEvent
import dev.leonlatsch.avivchallange.listings.view.list.ListingsUiState
import dev.leonlatsch.avivchallange.theming.theme.AVIVChallangeTheme
import dev.leonlatsch.avivchallange.theming.theme.textStyleBig
import dev.leonlatsch.avivchallange.theming.theme.textStyleMedium

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
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "${uiState.numberOfListings} listing(s) found",
                style = textStyleMedium()
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                items(uiState.listings, key = { it.id }) { listing ->
                    ListingItem(
                        listing = listing,
                        onClick = { handleUiEvent(ListingsUiEvent.OpenListing(listing.id)) },
                    )
                }
            }
        }

        PullRefreshIndicator(uiState.isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun ListingItem(
    listing: ListingViewData,
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
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    )
                } else {
                    AsyncImage(
                        model = listing.url,
                        contentDescription = null,
                        fallback = painterResource(R.mipmap.placeholder),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    )
                }

                Text(
                    text = listing.professional,
                    style = textStyleBig(),
                    color = Color.White,
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
                Text(text = listing.price, style = textStyleBig())
                Text(text = listing.area, style = textStyleMedium())
                when {
                    listing.bedrooms != null -> Text(text = "${listing.bedrooms} bedrooms", style = textStyleMedium())
                    listing.rooms != null -> Text(text = "${listing.rooms} total rooms", style = textStyleMedium())
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(painter = painterResource(R.drawable.ic_house), contentDescription = null)
                Text(text = listing.propertyType, style = textStyleMedium())
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(painter = painterResource(R.drawable.ic_location), contentDescription = null)
                Text(text = listing.city, style = textStyleMedium())
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
                    ListingViewData(
                        id = 1,
                        bedrooms = "4",
                        city = "Hamburg",
                        area = "200 M²",
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = "150.000 €",
                        professional = "GSL EXPLORE",
                        propertyType = "Maison - Villa",
                        rooms = "8",
                    ),
                    ListingViewData(
                        id = 2,
                        bedrooms = null,
                        city = "Hamburg",
                        area = "200 M²",
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = "150.000 €",
                        professional = "GSL EXPLORE",
                        propertyType = "Maison - Villa",
                        rooms = "8",
                    ),
                    ListingViewData(
                        id = 3,
                        bedrooms = null,
                        city = "Hamburg",
                        area = "200 M²",
                        url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                        price = "150.000 €",
                        professional = "GSL EXPLORE",
                        propertyType = "Maison - Villa",
                        rooms = null,
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