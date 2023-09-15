package dev.leonlatsch.avivchallange.listings.domain

import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    fun observeListings(): Flow<List<Listing>>
    fun observeListingDetail(listingId: Int): Flow<Listing>
    suspend fun loadListings()
    suspend fun loadListingDetail(listingId: Int)
}