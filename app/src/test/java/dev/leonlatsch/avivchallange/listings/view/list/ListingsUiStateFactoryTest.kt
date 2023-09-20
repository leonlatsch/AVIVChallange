package dev.leonlatsch.avivchallange.listings.view.list

import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingViewData
import dev.leonlatsch.avivchallange.listings.view.ListingListingViewDataMapperFake
import org.junit.Assert.*
import org.junit.Test

class ListingsUiStateFactoryTest {

    private val listingDomainToUiMapperFake = ListingListingViewDataMapperFake()

    private val factoryUnderTest = ListingsUiStateFactory(listingDomainToUiMapperFake)

    @Test
    fun `create() returns content when list not empty`() {
        val actual = factoryUnderTest.create(listOf(FullListing, FullListing), LoadingState.NotLoading, ErrorState.NoError)

        val expected = ListingsUiState.Content(
            listings = listOf(FullListingViewData, FullListingViewData),
            numberOfListings = 2,
            isRefreshing = false,
            hasError = false,
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `create() returns content with loading and error`() {
        val actual = factoryUnderTest.create(listOf(FullListing, FullListing), LoadingState.Loading, ErrorState.Error)

        val expected = ListingsUiState.Content(
            listings = listOf(FullListingViewData, FullListingViewData),
            numberOfListings = 2,
            isRefreshing = true,
            hasError = true,
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `create() returns loading when list empty and loading`() {
        val actual = factoryUnderTest.create(emptyList(), LoadingState.Loading, ErrorState.NoError)

        val expected = ListingsUiState.Loading
        assertEquals(expected, actual)
    }

    @Test
    fun `create() returns error`() {
        val actual = factoryUnderTest.create(emptyList(), LoadingState.NotLoading, ErrorState.Error)

        val expected = ListingsUiState.Error
        assertEquals(expected, actual)
    }
}