package dev.leonlatsch.avivchallange.listings.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingResponse(
    val id: Int,
    val bedrooms: Int? = null,
    val city: String,
    val area: Float,
    val url: String? = null,
    val price: Float,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int? = null,
)
