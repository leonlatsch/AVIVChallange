package dev.leonlatsch.avivchallange.listings.view

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import java.text.NumberFormat
import javax.inject.Inject

class ListingDomainToUiMapper @Inject constructor(
    private val currencyFormat: NumberFormat,
) : Mapper<Listing, ListingViewData> {

    override fun map(from: Listing): ListingViewData = with(from) {
        ListingViewData(
            id = id,
            bedrooms = bedrooms.toString(),
            city = city,
            area = "$area M²",
            url = url,
            price = currencyFormat.format(price),
            professional = professional,
            propertyType = propertyType,
            rooms = rooms.toString()
        )
    }

}