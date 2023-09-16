package dev.leonlatsch.avivchallange.listings.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.leonlatsch.avivchallange.listings.view.ListingsScreenViewModelImpl
import dev.leonlatsch.avivchallange.listings.view.ListingsUiState

const val LISTINGS_SCREEN_NAV_PATH = "/listings"

@Composable
fun ListingsScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<ListingsScreenViewModelImpl>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is ListingsUiState.Content -> ListingsContent(
            uiState = uiState as ListingsUiState.Content,
            handleUiEvent = { viewModel.handleUiEvent(it) }
        )
        is ListingsUiState.Error -> ListingsError()
        is ListingsUiState.Loading -> ListingsLoading()
    }
}