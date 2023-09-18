package dev.leonlatsch.avivchallange.listings.domain

import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.domain.model.Listing

interface ListingRepository {
    suspend fun getListings(): Result<List<Listing>>
    suspend fun getListingDetail(listingId: Int): Result<Listing>
}