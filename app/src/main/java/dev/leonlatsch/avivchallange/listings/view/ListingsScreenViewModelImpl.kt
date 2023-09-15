package dev.leonlatsch.avivchallange.listings.view

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListingsScreenViewModelImpl @Inject constructor() : ListingsScreenViewModel() {

    override val uiState: StateFlow<ListingsUiState> = MutableStateFlow(ListingsUiState.Loading)
}