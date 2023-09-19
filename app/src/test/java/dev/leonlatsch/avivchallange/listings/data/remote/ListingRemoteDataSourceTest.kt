package dev.leonlatsch.avivchallange.listings.data.remote

import android.net.http.HttpException
import android.net.http.NetworkException
import android.util.Log
import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingsResponse
import dev.leonlatsch.avivchallange.listings.testdata.FullListingResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import timber.log.Timber
import java.io.IOException

class ListingRemoteDataSourceTest {

    private val apiMock: ListingApi = mockk()

    private val dataSourceUnderTest = ListingRemoteDataSource(apiMock)

    @Before
    fun setUp() {
        Timber.uprootAll()
    }

    @Test
    fun `getListings() returns success with response`() = runTest {
        val response = ListingsResponse(
            items = listOf(FullListingResponse),
            totalCount = 1,
        )
        coEvery { apiMock.getListings() } returns response

        val actual = dataSourceUnderTest.getListings()

        assertTrue(actual is Result.Success)
        assertEquals(response, (actual as Result.Success).data)
    }

    @Test
    fun `getListings() returns error on IOException`() = runTest {
        val exception = HttpException("No internet", IOException())
        coEvery { apiMock.getListings() } throws exception

        val actual = dataSourceUnderTest.getListings()

        assertTrue(actual is Result.Error)
        assertEquals(exception, (actual as Result.Error).error)
        verify { Log.d(any(), any()) }
    }

    @Test
    fun `getListingDetail() returns success with response`() = runTest {
        val listingId = 1

        coEvery { apiMock.getListingDetail(listingId) } returns FullListingResponse

        val actual = dataSourceUnderTest.getListingDetail(listingId)

        assertTrue(actual is Result.Success)
        assertEquals(FullListingResponse, (actual as Result.Success).data)
    }

    @Test
    fun `getListingDetail() returns error on IOException`() = runTest {
        val exception = HttpException("No internet", IOException())
        coEvery { apiMock.getListingDetail(any()) } throws exception

        val actual = dataSourceUnderTest.getListingDetail(1)

        assertTrue(actual is Result.Error)
        assertEquals(exception, (actual as Result.Error).error)
        verify { Log.d(any(), any()) }
    }
}