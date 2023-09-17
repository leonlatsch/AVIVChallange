package dev.leonlatsch.avivchallange.listings.view.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class ListingDetailViewModel : ViewModel() {
    abstract val uiState: StateFlow<ListingDetailUiState>
}

