package dev.leonlatsch.avivchallange.listings.view.detail.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.leonlatsch.avivchallange.R
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailUiEvent
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailUiState
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import dev.leonlatsch.avivchallange.theming.theme.textStyleBig
import dev.leonlatsch.avivchallange.theming.theme.textStyleMedium
import dev.leonlatsch.avivchallange.theming.theme.textStyleSmall

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListingDetailContent(
    uiState: ListingDetailUiState.Content,
    handleUiEvent: (ListingDetailUiEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    LaunchedEffect(uiState.hasError) {
        if (uiState.hasError) {
            snackbarHostState.showSnackbar("Error refreshing listing detail")
        }
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = { handleUiEvent(ListingDetailUiEvent.Refresh) })

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(R.mipmap.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            } else {
                AsyncImage(
                    model = uiState.listingDetail.url,
                    contentDescription = null,
                    fallback = painterResource(R.mipmap.placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = uiState.listingDetail.propertyType,
                    style = textStyleBig(),
                    fontSize = 20.sp,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(text = uiState.listingDetail.price, style = textStyleBig())
                        Text(text = "Price", style = textStyleSmall())
                    }
                    Column {
                        Text(text = uiState.listingDetail.area, style = textStyleMedium())
                        Text(text = "Living Space", style = textStyleSmall())
                    }
                    Column {
                        when {
                            uiState.listingDetail.bedrooms != null -> {
                                Text(
                                    text = uiState.listingDetail.bedrooms,
                                    style = textStyleMedium()
                                )
                                Text(text = "Bedrooms", style = textStyleSmall())
                            }

                            uiState.listingDetail.rooms != null -> {
                                Text(text = uiState.listingDetail.rooms, style = textStyleMedium())
                                Text(text = "Rooms total", style = textStyleSmall())
                            }
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                    Text(text = uiState.listingDetail.city, style = textStyleBig())
                }

                Text(text = uiState.listingDetail.professional, style = textStyleMedium())
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ListingDetailContentPreview() {
    ListingDetailContent(
        uiState = ListingDetailUiState.Content(
            listingDetail = ListingViewData(
                id = 1,
                bedrooms = "4",
                city = "Hamburg",
                area = "200 m²",
                url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                price = "150.000 €",
                professional = "GSL EXPLORE",
                propertyType = "Maison - Villa",
                rooms = "8",
            ),
            isRefreshing = false,
            hasError = false,
        ),
        handleUiEvent = {},
        snackbarHostState = SnackbarHostState(),
    )
}