package dev.leonlatsch.avivchallange.listings.view.list

import app.cash.turbine.test
import dev.leonlatsch.avivchallange.CoroutineTestRule
import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingsUseCase
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingViewData
import dev.leonlatsch.avivchallange.listings.view.navigation.ListingsScreenNavigationEvent
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ListingsViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val twoListings = listOf(FullListing, FullListing)

    private val contentUiState = ListingsUiState.Content(
        listings = listOf(FullListingViewData, FullListingViewData),
        numberOfListings = 2,
        isRefreshing = false,
        hasError = false,
    )

    private val getListingsUseCaseMock: GetListingsUseCase = mockk {
        coEvery { this@mockk.invoke() } returns Result.Success(twoListings)
    }
    private val listingsUiStateFactoryMock: ListingsUiStateFactory = mockk {
        coEvery { create(emptyList(), LoadingState.Loading, any()) } returns ListingsUiState.Loading
        coEvery { create(emptyList(), LoadingState.NotLoading, any()) } returns ListingsUiState.Error

        coEvery { create(twoListings, LoadingState.NotLoading, ErrorState.NoError) } returns contentUiState
        coEvery { create(twoListings, LoadingState.NotLoading, ErrorState.Error) } returns contentUiState.copy(hasError = true)
    }

    private fun createViewModelUnderTest() = ListingsViewModel(getListingsUseCaseMock, listingsUiStateFactoryMock)

    @Test
    fun `uiState loading - content - content with error`() = runTest {
        val viewModelUnderTest = createViewModelUnderTest() // Ensures that the viewModel is created in correct coroutine scope

        viewModelUnderTest.uiState.test {

            // Refresh -> Loading, Content
            viewModelUnderTest.refresh()
            assertEquals(ListingsUiState.Loading, awaitItem())

            val content = awaitItem()
            assertEquals(contentUiState, content)

            // Refresh again but with error

            coEvery { getListingsUseCaseMock.invoke() } returns Result.Error()

            val expectedUiState = contentUiState.copy(hasError = true)

            viewModelUnderTest.refresh()
            assertEquals(expectedUiState, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState loading - error - content`() = runTest {
        val viewModelUnderTest = createViewModelUnderTest() // Ensures that the viewModel is created in correct coroutine scope

        coEvery { getListingsUseCaseMock.invoke() } returns Result.Error()

        viewModelUnderTest.uiState.test {

            // Refresh -> Loading, Content
            viewModelUnderTest.refresh()
            assertEquals(ListingsUiState.Loading, awaitItem())

            val content = awaitItem()
            assertEquals(ListingsUiState.Error, content)

            // Refresh again but with content

            coEvery { getListingsUseCaseMock.invoke() } returns Result.Success(twoListings)

            viewModelUnderTest.refresh()
            assertEquals(contentUiState, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `handleUiEvent(Refresh) triggers refresh`() {
        val viewModelUnderTest = createViewModelUnderTest()

        viewModelUnderTest.handleUiEvent(ListingsUiEvent.Refresh)

        coVerify { getListingsUseCaseMock.invoke() }
    }

    @Test
    fun `handleUiEvent(OpenListing) sets navigation event`() = runTest {
        val viewModelUnderTest = createViewModelUnderTest()

        viewModelUnderTest.eventFlow.test {
            viewModelUnderTest.handleUiEvent(ListingsUiEvent.OpenListing(1))

            val navEvent = awaitItem()

            val expectedEvent = ListingsScreenNavigationEvent.OpenDetailScreen(1)
            assertEquals(expectedEvent, navEvent)

            cancelAndIgnoreRemainingEvents()
        }
    }
}