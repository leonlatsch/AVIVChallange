package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.domain.ListingRepositoryFake
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class GetListingsUseCaseTest {

    private val listingRepositoryFake = ListingRepositoryFake()

    private val useCaseUnderTest = GetListingsUseCase(listingRepositoryFake)

    @Test
    fun `invoke() delegates to repo`() = runTest {
        listingRepositoryFake.getListingsShouldReturn = Result.Success(listOf(FullListing, FullListing))

        val actual = useCaseUnderTest.invoke()

        val expected = Result.Success(listOf(FullListing, FullListing))
        assertEquals(expected, actual)
    }
}