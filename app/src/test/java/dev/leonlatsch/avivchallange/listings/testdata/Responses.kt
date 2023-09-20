package dev.leonlatsch.avivchallange.listings.testdata

import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.domain.model.Listing

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

val FullListing = Listing(
    id = 1,
    bedrooms = 4,
    city = "Hamburg",
    area = 200f,
    url = "url",
    price = 120f,
    professional = "professional",
    propertyType = "type",
    offerType = 0,
    rooms = 8,
)