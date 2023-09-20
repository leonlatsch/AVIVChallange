package dev.leonlatsch.avivchallange.listings.view

import dev.leonlatsch.avivchallange.listings.testdata.FullListing
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import java.text.NumberFormat

class ListingDomainToUiMapperTest {

    private val currencyFormatMock: NumberFormat = mockk {
        every { format(any<Float>()) } answers {
            "${firstArg<Float>()} €" // Just append € for testing
        }
    }

    private val mapperUnderTest = ListingDomainToUiMapper(currencyFormatMock)

    @Test
    fun `map() maps values`() {
        val mappedListing = with(FullListing) {
            ListingViewData(
                id = id,
                bedrooms = bedrooms.toString(),
                city = city,
                area = "$area M²",
                url = url,
                price = "$price €",
                professional = professional,
                propertyType = propertyType,
                rooms = rooms.toString()
            )
        }

        val actual = mapperUnderTest.map(FullListing)

        assertEquals(mappedListing, actual)
    }
}