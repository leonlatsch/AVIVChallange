package dev.leonlatsch.avivchallange.listings.view

import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.testdata.FullListingViewData
import dev.leonlatsch.avivchallange.listings.view.model.ListingViewData

class ListingListingViewDataMapperFake : Mapper<Listing, ListingViewData> {
    var mappedValue = FullListingViewData
    override fun map(from: Listing): ListingViewData = mappedValue
}