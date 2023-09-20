package dev.leonlatsch.avivchallange.listings.view.detail

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.core.view.state.ErrorState
import dev.leonlatsch.avivchallange.core.view.state.LoadingState
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingViewData
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import org.junit.Assert.*
import org.junit.Test

class ListingDetailUiStateFactoryTest {

    private val listingDomainToUiMapperFake = object : Mapper<Listing, ListingViewData> {
        var mappedValue = FullListingViewData
        override fun map(from: Listing): ListingViewData = mappedValue
    }

    private val factoryUnderTest = ListingDetailUiStateFactory(listingDomainToUiMapperFake)

    @Test
    fun `create() return content when model not null`() {
        val actual = factoryUnderTest.create(FullListing, LoadingState.NotLoading, ErrorState.NoError)

        val expected = ListingDetailUiState.Content(
            listingDetail = FullListingViewData,
            isRefreshing = false,
            hasError = false,
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `create() returns content with error and loading`() {
        val actual = factoryUnderTest.create(FullListing, LoadingState.Loading, ErrorState.Error)

        val expected = ListingDetailUiState.Content(
            listingDetail = FullListingViewData,
            isRefreshing = true,
            hasError = true,
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `create() returns loading when model null and loading`() {
        val actual = factoryUnderTest.create(null, LoadingState.Loading, ErrorState.NoError)

        val expected = ListingDetailUiState.Loading
        assertEquals(expected, actual)
    }

    @Test
    fun `create() returns error`() {
        val actual = factoryUnderTest.create(null, LoadingState.NotLoading, ErrorState.Error)

        val expected = ListingDetailUiState.Error
        assertEquals(expected, actual)
    }
}