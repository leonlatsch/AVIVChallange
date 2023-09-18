package dev.leonlatsch.avivchallange.listings.view.detail.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ListingDetailError() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Error loading listing detail", modifier = Modifier.align(Alignment.Center))
    }
}