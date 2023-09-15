package dev.leonlatsch.avivchallange.listings.data.remote

import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingApi {

    @GET("listings.json")
    fun getListings(): ListingsResponse

    @GET("listings/{listingId}.json")
    fun getListingDetail(@Path("listingId") listingId: Int): ListingResponse
}