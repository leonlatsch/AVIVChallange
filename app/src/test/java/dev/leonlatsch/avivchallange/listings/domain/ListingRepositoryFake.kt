package dev.leonlatsch.avivchallange.listings.domain

import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.listings.testdata.FullListing

class ListingRepositoryFake : ListingRepository {

    var getListingsShouldReturn: Result<List<Listing>> = Result.Success(emptyList())
    override suspend fun getListings(): Result<List<Listing>> {
        return getListingsShouldReturn
    }

    var getListingDetailShouldReturn: Result<Listing> = Result.Success(FullListing)
    override suspend fun getListingDetail(listingId: Int): Result<Listing> {
        return getListingDetailShouldReturn
    }
}