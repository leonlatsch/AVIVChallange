package dev.leonlatsch.avivchallange.listings.testdata

import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData

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
    price = 1284f,
    professional = "professional",
    propertyType = "type",
    offerType = 0,
    rooms = 8,
)

val FullListingViewData = ListingViewData(
    id = 1,
    bedrooms = "4",
    city = "Hamburg",
    area = "200.0 M²",
    url = "url",
    price = "1284.0 €",
    professional = "professional",
    propertyType = "type",
    rooms = "8",
)