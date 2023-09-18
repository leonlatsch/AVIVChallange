package dev.leonlatsch.avivchallange.listings.view

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

class ListingDomainToUiMapper @Inject constructor() : Mapper<Listing, ListingViewData> {

    private val currencyFormat by lazy {
        NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
            currency = Currency.getInstance("EUR")
        }
    }

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