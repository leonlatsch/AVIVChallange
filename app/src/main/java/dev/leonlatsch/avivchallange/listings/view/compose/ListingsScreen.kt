package dev.leonlatsch.avivchallange.listings.view.compose

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.leonlatsch.avivchallange.listings.view.ListingsScreenViewModelImpl

const val LISTINGS_SCREEN_NAV_PATH = "/listings"

@Composable
fun ListingsScreen() {
    val viewModel = hiltViewModel<ListingsScreenViewModelImpl>()
}