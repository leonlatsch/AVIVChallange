package dev.leonlatsch.avivchallange.listings.data.remote

import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingApi {

    @GET("listings.json")
    suspend fun getListings(): ListingsResponse

    @GET("listings/{listingId}.json")
    suspend fun getListingDetail(@Path("listingId") listingId: Int): ListingResponse
}