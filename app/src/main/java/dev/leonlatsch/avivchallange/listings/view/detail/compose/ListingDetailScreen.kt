package dev.leonlatsch.avivchallange.listings.view.detail.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.leonlatsch.avivchallange.R
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailUiState
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailViewModel

const val ARG_LISTING_DETAIL = "listingId"
const val LISTING_DETAIL_SCREEN_NAV_ROUTE = "/listings/{$ARG_LISTING_DETAIL}"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingDetailScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<ListingDetailViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(0) {
        viewModel.refresh()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listing Detail") },
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = null,
                        modifier = Modifier.clickable { navController.navigateUp() }
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            when (uiState) {
                is ListingDetailUiState.Content -> ListingDetailContent(uiState as ListingDetailUiState.Content)
                is ListingDetailUiState.Loading -> ListingDetailLoading()
                is ListingDetailUiState.Error -> ListingDetailError()
            }
        }
    }
}