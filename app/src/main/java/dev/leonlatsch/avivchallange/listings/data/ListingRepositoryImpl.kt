package dev.leonlatsch.avivchallange.listings.data

import dev.leonlatsch.avivchallange.listings.data.mapper.ListingResponseToDomainMapper
import dev.leonlatsch.avivchallange.listings.data.remote.ListingRemoteDataSource
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.core.model.Result
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val remoteDataSource: ListingRemoteDataSource,
    private val listingResponseToDomainMapper: ListingResponseToDomainMapper,
) : ListingRepository {

    override suspend fun getListings(): Result<List<Listing>> =
        when (val networkResult = remoteDataSource.getListings()) {
            is Result.Success -> {
                val listings = networkResult.data.items.map { listingResponseToDomainMapper.map(it) }
                Result.Success(listings)
            }
            is Result.Error -> Result.Error(networkResult.error)
        }

    override suspend fun getListingDetail(listingId: Int): Result<Listing> {
        return when (val networkResult = remoteDataSource.getListingDetail(listingId)) {
            is Result.Success -> {
                val listing = listingResponseToDomainMapper.map(networkResult.data)
                Result.Success(listing)
            }
            is Result.Error -> Result.Error(networkResult.error)
        }
    }
}