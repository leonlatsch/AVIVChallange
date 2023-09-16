package dev.leonlatsch.avivchallange.listings.data

import dev.leonlatsch.avivchallange.listings.data.local.ListingLocalDataSource
import dev.leonlatsch.avivchallange.listings.data.mapper.ListingEntityToDomainMapper
import dev.leonlatsch.avivchallange.listings.data.mapper.ListingResponseToEntityMapper
import dev.leonlatsch.avivchallange.listings.data.remote.ListingRemoteDataSource
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
import dev.leonlatsch.avivchallange.core.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val localDataSource: ListingLocalDataSource,
    private val remoteDataSource: ListingRemoteDataSource,
    private val listingEntityToDomainMapper: ListingEntityToDomainMapper,
    private val listingResponseToEntityMapper: ListingResponseToEntityMapper,
) : ListingRepository {

    override fun observeListings(): Flow<List<Listing>> =
        localDataSource.observeListings().map {  listings ->
            listings.map { listingEntityToDomainMapper.map(it) }
        }

    override fun observeListingDetail(listingId: Int): Flow<Listing> =
        localDataSource.observeListing(listingId).map { listingDetail ->
            listingEntityToDomainMapper.map(listingDetail)
        }

    override suspend fun loadListings(): Result<Unit> =
        when (val result = remoteDataSource.getListings()) {
            is Result.Success -> {
                val listingEntities = result.data.items.map { listingResponseToEntityMapper.map(it) }
                localDataSource.insertListings(listingEntities)
            }
            is Result.Error -> Result.Error(result.error)
        }

    override suspend fun loadListingDetail(listingId: Int): Result<Unit> {
        return when (val result = remoteDataSource.getListingDetail(listingId)) {
            is Result.Success -> {
                val listingDetailEntity = listingResponseToEntityMapper.map(result.data)
                localDataSource.insertListingDetail(listingDetailEntity)
            }
            is Result.Error -> Result.Error(result.error)
        }
    }
}