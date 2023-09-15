package dev.leonlatsch.avivchallange.listings.data

import dev.leonlatsch.avivchallange.listings.data.local.ListingLocalDataSource
import dev.leonlatsch.avivchallange.listings.data.remote.ListingRemoteDataSource
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val localDataSource: ListingLocalDataSource,
    private val remoteDataSource: ListingRemoteDataSource,
) : ListingRepository {

    override fun observeListings(): Flow<List<Listing>> {
        TODO("Not yet implemented")
    }

    override fun observeListingDetail(listingId: Int): Flow<Listing> {
        TODO("Not yet implemented")
    }

    override suspend fun loadListings() {
        TODO("Not yet implemented")
    }

    override suspend fun loadListingDetail(listingId: Int) {
        TODO("Not yet implemented")
    }


}