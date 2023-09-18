package dev.leonlatsch.avivchallange.listings.domain

import dev.leonlatsch.avivchallange.core.Result
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    suspend fun getListings(): Result<List<Listing>>
    suspend fun getListingDetail(listingId: Int): Result<Listing>
}