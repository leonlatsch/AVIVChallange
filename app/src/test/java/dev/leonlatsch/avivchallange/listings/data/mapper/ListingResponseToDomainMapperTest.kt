package dev.leonlatsch.avivchallange.listings.data.mapper

import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingResponse
import org.junit.Assert.*
import org.junit.Test

class ListingResponseToDomainMapperTest {

    private val mapperUnderTest = ListingResponseToDomainMapper()

    @Test
    fun `map() maps correct values`() {
        val response = FullListingResponse

        val actual = mapperUnderTest.map(response)

        val expected = Listing(
            id = 1,
            bedrooms = 4,
            city = "Hamburg",
            area = 200f,
            url = "url",
            price = 1284f,
            professional = "professional",
            propertyType = "type",
            offerType = 0,
            rooms = 8,
        )

        assertEquals(expected, actual)
    }
}