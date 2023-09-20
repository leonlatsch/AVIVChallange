package dev.leonlatsch.avivchallange.listings.domain.usecase

import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.domain.ListingRepositoryFake
import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetListingDetailUseCaseTest {

    private val listingRepositoryFake = ListingRepositoryFake()

    private val useCaseUnderTest = GetListingDetailUseCase(listingRepositoryFake)

    @Test
    fun `invoke() delegates to repo`() = runTest{
        listingRepositoryFake.getListingDetailShouldReturn = Result.Success(FullListing)

        val actual = useCaseUnderTest.invoke(1)

        val expected = Result.Success(FullListing)
        assertEquals(expected, actual)
    }
}