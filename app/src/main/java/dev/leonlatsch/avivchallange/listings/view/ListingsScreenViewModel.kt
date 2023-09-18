package dev.leonlatsch.avivchallange.listings.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class ListingsScreenViewModel : ViewModel() {
    abstract val uiState: StateFlow<ListingsUiState>
    abstract fun refresh()
    abstract fun handleUiEvent(event: ListingsUiEvent)
}