package dev.leonlatsch.avivchallange.listings.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ListingDetailScreenNavRoute

@Composable
fun LaunchNavigationEffect(event: ListingsScreenNavigationEvent?, navController: NavController) {
    LaunchedEffect(event) {
        when (event) {
            is ListingsScreenNavigationEvent.OpenDetailScreen -> {
                navController.navigate(ListingDetailScreenNavRoute.buildRoute(event.listingId))
            }
            else -> Unit
        }
    }
}
