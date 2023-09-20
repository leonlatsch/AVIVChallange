package dev.leonlatsch.avivchallange.listings.view.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import dev.leonlatsch.avivchallange.CoroutineTestRule
import dev.leonlatsch.avivchallange.listings.data.ListingRepositoryImpl
import dev.leonlatsch.avivchallange.listings.data.mapper.ListingResponseToDomainMapper
import dev.leonlatsch.avivchallange.listings.data.remote.ListingApi
import dev.leonlatsch.avivchallange.listings.data.remote.ListingRemoteDataSource
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingDetailUseCase
import dev.leonlatsch.avivchallange.listings.testdata.FullListingResponse
import dev.leonlatsch.avivchallange.listings.testdata.FullListingViewData
import dev.leonlatsch.avivchallange.listings.view.ListingDomainToUiMapper
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ListingDetailScreenNavRoute
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class ListingDetailViewModelIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val listingId = 1

    private val contentUiState = ListingDetailUiState.Content(
        listingDetail = FullListingViewData,
        isRefreshing = false,
        hasError = false,
    )

    private val savedStateHandle =
        SavedStateHandle(mapOf(ListingDetailScreenNavRoute.ARG_LISTING_DETAIL_ID to listingId))

    private val apiMock: ListingApi = mockk {
        coEvery { getListingDetail(listingId) } returns FullListingResponse
    }
    private val currencyFormatMock: NumberFormat = mockk {
        every { format(any<Float>()) } answers {
            "${firstArg<Float>()} €" // Just append € for testing
        }
    }

    private fun createViewModelUnderTest() = ListingDetailViewModel(
        savedStateHandle,
        GetListingDetailUseCase(
            ListingRepositoryImpl(
                ListingRemoteDataSource(apiMock),
                ListingResponseToDomainMapper()
            )
        ),
        ListingDetailUiStateFactory(
            ListingDomainToUiMapper(currencyFormatMock)
        ),
    )

    @Test
    fun `uiState emits values from api`() = runTest {
        val viewModelUnderTest = createViewModelUnderTest() // Ensures that the viewModel is created in correct coroutine scope

        viewModelUnderTest.uiState.test {

            coEvery { apiMock.getListingDetail(listingId) } returns FullListingResponse
            viewModelUnderTest.refresh()
            assertEquals(ListingDetailUiState.Loading, awaitItem())
            val content = awaitItem()
            assertEquals(contentUiState, content)
        }
    }
}