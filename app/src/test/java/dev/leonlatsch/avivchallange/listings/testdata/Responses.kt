package dev.leonlatsch.avivchallange.listings.testdata

import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse

val FullListingResponse = ListingResponse(
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