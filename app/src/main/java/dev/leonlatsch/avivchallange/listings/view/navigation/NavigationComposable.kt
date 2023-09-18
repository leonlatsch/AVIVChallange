package dev.leonlatsch.avivchallange.listings.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ARG_LISTING_DETAIL
import dev.leonlatsch.avivchallange.listings.view.detail.compose.LISTING_DETAIL_SCREEN_NAV_ROUTE

@Composable
fun LaunchNavigationEffect(event: ListingsScreenNavigationEvent?, navController: NavController) {
    LaunchedEffect(event) {
        when (event) {
            is ListingsScreenNavigationEvent.OpenDetailScreen -> {
                navController.navigate(
                    LISTING_DETAIL_SCREEN_NAV_ROUTE.replace(
                        "{$ARG_LISTING_DETAIL}",
                        event.listingId.toString(),
                    )
                )
            }
            else -> Unit
        }
    }
}
