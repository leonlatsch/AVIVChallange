package dev.leonlatsch.avivchallange.listings.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class ListingsScreenViewModel : ViewModel() {
    abstract val uiState: StateFlow<ListingsUiState>
}