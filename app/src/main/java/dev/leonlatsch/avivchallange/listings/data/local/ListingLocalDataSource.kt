package dev.leonlatsch.avivchallange.listings.data.local

import android.util.Log
import dev.leonlatsch.avivchallange.core.Result
import dev.leonlatsch.avivchallange.listings.data.local.model.ListingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.io.IOException
import javax.inject.Inject

class ListingLocalDataSource @Inject constructor(
    private val listingDao: ListingDao,
) {

    fun observeListings(): Flow<List<ListingEntity>> = try {
        listingDao.selectListings()
    } catch (e: IOException) {
        Log.e(ListingLocalDataSource::class.simpleName, "Error loading listings from database: $e")
        emptyFlow()
    }

    fun observeListing(listingId: Int): Flow<ListingEntity> = try {
        listingDao.selectListingDetail(listingId)
    } catch (e: IOException) {
        Log.e(ListingLocalDataSource::class.simpleName, "Error loading listing $listingId from database: $e")
        emptyFlow()
    }

    suspend fun insertListings(listings: List<ListingEntity>): Result<Unit> = try {
        listingDao.insertListings(listings)
        Result.Success(Unit)
    } catch (e: IOException) {
        Log.e(ListingLocalDataSource::class.simpleName, "Error inserting listings: $e")
        Result.Error(e)
    }

    suspend fun insertListingDetail(listingDetail: ListingEntity): Result<Unit> = try {
        listingDao.insertListingDetail(listingDetail)
        Result.Success(Unit)
    } catch (e: IOException) {
        Log.e(ListingLocalDataSource::class.simpleName, "Error inserting listing: $e")
        Result.Error(e)
    }
}