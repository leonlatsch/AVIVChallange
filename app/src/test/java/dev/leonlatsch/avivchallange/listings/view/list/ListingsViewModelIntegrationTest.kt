package dev.leonlatsch.avivchallange.listings.view.list

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import dev.leonlatsch.avivchallange.CoroutineTestRule
import dev.leonlatsch.avivchallange.listings.data.ListingRepositoryImpl
import dev.leonlatsch.avivchallange.listings.data.mapper.ListingResponseToDomainMapper
import dev.leonlatsch.avivchallange.listings.data.remote.ListingApi
import dev.leonlatsch.avivchallange.listings.data.remote.ListingRemoteDataSource
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingsResponse
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingDetailUseCase
import dev.leonlatsch.avivchallange.listings.domain.usecase.GetListingsUseCase
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingResponse
import dev.leonlatsch.avivchallange.listings.testdata.FullListingViewData
import dev.leonlatsch.avivchallange.listings.view.ListingDomainToUiMapper
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailUiState
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailUiStateFactory
import dev.leonlatsch.avivchallange.listings.view.detail.ListingDetailViewModel
import dev.leonlatsch.avivchallange.listings.view.detail.compose.ListingDetailScreenNavRoute
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class ListingsViewModelIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val twoListingsResponse = ListingsResponse(
        items = listOf(FullListingResponse, FullListingResponse),
        totalCount = 2,
    )

    private val contentUiState = ListingsUiState.Content(
        listings = listOf(FullListingViewData, FullListingViewData),
        numberOfListings = 2,
        isRefreshing = false,
        hasError = false,
    )

    private val apiMock: ListingApi = mockk {
        coEvery { getListings() } returns twoListingsResponse
    }
    private val currencyFormatMock: NumberFormat = mockk {
        every { format(any<Float>()) } answers {
            "${firstArg<Float>()} €" // Just append € for testing
        }
    }

    private fun createViewModelUnderTest() = ListingsViewModel(
        GetListingsUseCase(
            ListingRepositoryImpl(
                ListingRemoteDataSource(apiMock),
                ListingResponseToDomainMapper()
            )
        ),
        ListingsUiStateFactory(
            ListingDomainToUiMapper(currencyFormatMock)
        )
    )

    @Test
    fun `uiState emits values from api`() = runTest {
        val viewModel = createViewModelUnderTest()

        viewModel.uiState.test {
            coEvery { apiMock.getListings() } returns twoListingsResponse

            viewModel.refresh()
            assertEquals(ListingsUiState.Loading, awaitItem())
            val content = awaitItem()
            assertEquals(contentUiState, content)
        }
    }
}