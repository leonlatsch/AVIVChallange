package dev.leonlatsch.avivchallange.listings.view.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import dev.leonlatsch.avivchallange.CoroutineTestRule
import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingDetailUseCase
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingViewData
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ListingDetailScreenNavRoute
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ListingDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val listingId = 1

    private val contentUiState = ListingDetailUiState.Content(
        listingDetail = FullListingViewData,
        isRefreshing = false,
        hasError = false,
    )

    private val savedStateHandle = SavedStateHandle(mapOf(ListingDetailScreenNavRoute.ARG_LISTING_DETAIL_ID to listingId))

    private val getListingDetailUseCaseMock: GetListingDetailUseCase = mockk {
        coEvery { this@mockk.invoke(listingId) } returns Result.Success(FullListing)
    }
    private val listingDetailUiStateFactoryMock: ListingDetailUiStateFactory = mockk {
        coEvery { create(null, LoadingState.Loading, any()) } returns ListingDetailUiState.Loading
        coEvery { create(null, LoadingState.NotLoading, any()) } returns ListingDetailUiState.Error

        coEvery { create(FullListing, LoadingState.NotLoading, ErrorState.NoError) } returns contentUiState
        coEvery { create(FullListing, LoadingState.NotLoading, ErrorState.Error) } returns contentUiState.copy(hasError = true)
    }

    private fun createViewModelUnderTest() = ListingDetailViewModel(
        savedStateHandle,
        getListingDetailUseCaseMock,
        listingDetailUiStateFactoryMock,
    )

    @Test
    fun `uiState loading - content - content with error`() = runTest {
        val viewModelUnderTest = createViewModelUnderTest() // Ensures that the viewModel is created in correct coroutine scope

        viewModelUnderTest.uiState.test {

            // Refresh -> Loading, Content
            viewModelUnderTest.refresh()
            assertEquals(ListingDetailUiState.Loading, awaitItem())

            val content = awaitItem()
            assertEquals(contentUiState, content)

            // Refresh again but with error

            coEvery { getListingDetailUseCaseMock.invoke(listingId) } returns Result.Error()

            val expectedUiState = contentUiState.copy(hasError = true)

            viewModelUnderTest.refresh()
            assertEquals(expectedUiState, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState loading - error - content`() = runTest {
        val viewModelUnderTest = createViewModelUnderTest() // Ensures that the viewModel is created in correct coroutine scope

        coEvery { getListingDetailUseCaseMock.invoke(listingId) } returns Result.Error()

        viewModelUnderTest.uiState.test {

            // Refresh -> Loading, Content
            viewModelUnderTest.refresh()
            assertEquals(ListingDetailUiState.Loading, awaitItem())

            val content = awaitItem()
            assertEquals(ListingDetailUiState.Error, content)

            // Refresh again but with content

            coEvery { getListingDetailUseCaseMock.invoke(listingId) } returns Result.Success(FullListing)

            viewModelUnderTest.refresh()
            assertEquals(contentUiState, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `handleUiEvent(Refresh) triggers refresh`() {
        val viewModelUnderTest = createViewModelUnderTest()

        viewModelUnderTest.handleUiEvent(ListingDetailUiEvent.Refresh)

        coVerify { getListingDetailUseCaseMock.invoke(listingId) }
    }
}

