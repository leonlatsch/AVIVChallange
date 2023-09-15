package dev.leonlatsch.avivchallange.listings.data.mapper

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.listings.data.local.model.ListingEntity
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import javax.inject.Inject

class ListingResponseToEntityMapper @Inject constructor() : Mapper<ListingResponse, ListingEntity> {

    override fun map(from: ListingResponse): ListingEntity = with(from) {
        ListingEntity(
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