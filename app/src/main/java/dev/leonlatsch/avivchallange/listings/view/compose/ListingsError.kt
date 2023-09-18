package dev.leonlatsch.avivchallange.listings.view.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.leonlatsch.avivchallange.listings.view.ListingsUiEvent

@Composable
fun ListingsError(handleUiEvent: (ListingsUiEvent) -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {
       Column(
           modifier = Modifier.align(Alignment.Center),
           horizontalAlignment = Alignment.CenterHorizontally,
       ) {
           Text(
               text = "Error loading Listings",
               modifier = Modifier.align(Alignment.CenterHorizontally)
           )
           TextButton(onClick = { handleUiEvent(ListingsUiEvent.Refresh) }) {
               Text(text = "Retry")
           }
       }
    }
}