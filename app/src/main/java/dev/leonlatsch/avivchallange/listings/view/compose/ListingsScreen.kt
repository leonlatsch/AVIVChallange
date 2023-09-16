package dev.leonlatsch.avivchallange.listings.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.leonlatsch.avivchallange.listings.view.ListingsScreenViewModelImpl
import dev.leonlatsch.avivchallange.listings.view.ListingsUiState

const val LISTINGS_SCREEN_NAV_PATH = "/listings"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingsScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<ListingsScreenViewModelImpl>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Real Estate Listings") })
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when (uiState) {
                is ListingsUiState.Content -> ListingsContent(
                    uiState = uiState as ListingsUiState.Content,
                    handleUiEvent = { viewModel.handleUiEvent(it) },
                    snackbarHostState
                )

                is ListingsUiState.Error -> ListingsError()
                is ListingsUiState.Loading -> ListingsLoading()
            }
        }
    }
}