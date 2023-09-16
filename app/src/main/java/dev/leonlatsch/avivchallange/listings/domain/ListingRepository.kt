package dev.leonlatsch.avivchallange.listings.domain

import dev.leonlatsch.avivchallange.core.Result
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    fun observeListings(): Flow<List<Listing>>
    fun observeListingDetail(listingId: Int): Flow<Listing>
    suspend fun loadListings(): Result<Unit>
    suspend fun loadListingDetail(listingId: Int): Result<Unit>
}