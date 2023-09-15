package dev.leonlatsch.avivchallange.listings.data

import dev.leonlatsch.avivchallange.listings.data.local.ListingLocalDataSource
import dev.leonlatsch.avivchallange.listings.data.mapper.ListingEntityToDomainMapper
import dev.leonlatsch.avivchallange.listings.data.mapper.ListingResponseToEntityMapper
import dev.leonlatsch.avivchallange.listings.data.remote.ListingRemoteDataSource
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing
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

    override suspend fun loadListings() {
        remoteDataSource.getListings().onSuccess { listingsResponse ->
            val listingEntities = listingsResponse.items.map { listingResponseToEntityMapper.map(it) }
            localDataSource.insertListings(listingEntities)
        }
    }

    override suspend fun loadListingDetail(listingId: Int) {
        remoteDataSource.getListingDetail(listingId).onSuccess { listingDetailResponse ->
            val listingDetailEntity = listingResponseToEntityMapper.map(listingDetailResponse)
            localDataSource.insertListingDetail(listingDetailEntity)
        }
    }


}