package dev.leonlatsch.avivchallange.listings.data.remote

import dev.leonlatsch.avivchallange.core.model.Result
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingsResponse
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ListingRemoteDataSource @Inject constructor(
    private val listingApi: ListingApi,
) {

    suspend fun getListings(): Result<ListingsResponse> = try {
        val listings = listingApi.getListings()
        Result.Success(listings)
    } catch (e: IOException) {
        Timber.d("Error getting listings: $e")
        Result.Error(e)
    }

    suspend fun getListingDetail(listingId: Int): Result<ListingResponse> = try {
        val listings = listingApi.getListingDetail(listingId)
        Result.Success(listings)
    } catch (e: IOException) {
        Timber.d("Error getting listing detail: $e")
        Result.Error(e)
    }
}