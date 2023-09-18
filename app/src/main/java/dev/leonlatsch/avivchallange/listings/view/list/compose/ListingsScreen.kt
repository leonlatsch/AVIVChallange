package dev.leonlatsch.avivchallange.listings.view.list.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.leonlatsch.avivchallange.listings.view.list.ListingsScreenViewModel
import dev.leonlatsch.avivchallange.listings.view.list.ListingsUiState
import dev.leonlatsch.avivchallange.listings.view.navigation.LaunchNavigationEffect

const val LISTINGS_SCREEN_NAV_PATH = "/listings"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingsScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<ListingsScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(0) {
        viewModel.refresh()
    }

    val navigationEvent by viewModel.eventFlow.collectAsStateWithLifecycle(null)
    LaunchNavigationEffect(navigationEvent, navController)

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

                is ListingsUiState.Error -> ListingsError(handleUiEvent = { viewModel.handleUiEvent(it) })
                is ListingsUiState.Loading -> ListingsLoading()
            }
        }
    }
}