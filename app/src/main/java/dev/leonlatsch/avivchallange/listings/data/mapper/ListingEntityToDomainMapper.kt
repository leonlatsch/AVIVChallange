package dev.leonlatsch.avivchallange.listings.data.mapper

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.listings.data.local.model.ListingEntity
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import javax.inject.Inject

class ListingEntityToDomainMapper @Inject constructor() : Mapper<ListingEntity, Listing> {

    override fun map(from: ListingEntity): Listing = with(from) {
        Listing(
            id = id,
            bedrooms = bedrooms,
            city = city,
            area = area,
            url = url,
            price = price,
            professional = professional,
            propertyType = propertyType,
            offerType = offerType,
            rooms = rooms
        )
    }
}