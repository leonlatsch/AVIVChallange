package dev.leonlatsch.avivchallange.listings.data

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.data.remote.ListingRemoteDataSource
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingsResponse
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class ListingRepositoryImplTest {

    private val remoteDataSourceMock: ListingRemoteDataSource = mockk()
    private val listingResponseToDomainMapperFake = object : Mapper<ListingResponse, Listing> {
        var mappedValue: Listing = FullListing
        override fun map(from: ListingResponse): Listing {
            return mappedValue
        }
    }

    private val repositoryUnderTest = ListingRepositoryImpl(remoteDataSourceMock, listingResponseToDomainMapperFake)

    @Test
    fun `getListings() returns mapped data on success`() = runTest {
        val response = ListingsResponse(
            items = listOf(FullListingResponse, FullListingResponse),
            totalCount = 2,
        )
        coEvery { remoteDataSourceMock.getListings() } returns Result.Success(response)

        val actual = repositoryUnderTest.getListings()

        val expectedResult = listOf(FullListing, FullListing)

        assertTrue(actual is Result.Success)
        assertEquals(expectedResult, (actual as Result.Success).data)
    }

    @Test
    fun `getListings() returns error`() = runTest {
        val error = Throwable()

        coEvery { remoteDataSourceMock.getListings() } returns Result.Error(error)

        val actual = repositoryUnderTest.getListings()

        assertTrue(actual is Result.Error)
        assertEquals(error, (actual as Result.Error).error)
    }

    @Test
    fun `getListingDetail() returns mapped data on success`() = runTest {
        val response = FullListingResponse
        coEvery { remoteDataSourceMock.getListingDetail(any()) } returns Result.Success(response)

        val actual = repositoryUnderTest.getListingDetail(1)

        val expectedResult = FullListing

        assertTrue(actual is Result.Success)
        assertEquals(expectedResult, (actual as Result.Success).data)
    }

    @Test
    fun `getListingDetail() returns error`() = runTest {
        val error = Throwable()

        coEvery { remoteDataSourceMock.getListingDetail(any()) } returns Result.Error(error)

        val actual = repositoryUnderTest.getListingDetail(1)

        assertTrue(actual is Result.Error)
        assertEquals(error, (actual as Result.Error).error)
    }
}