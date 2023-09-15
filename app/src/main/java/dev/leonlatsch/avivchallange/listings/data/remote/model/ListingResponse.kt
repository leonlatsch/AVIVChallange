package dev.leonlatsch.avivchallange.listings.data.remote.model

import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingResponse(
    @PrimaryKey val id: Int,
    val bedrooms: Int,
    val city: String,
    val area: Float,
    val url: String,
    val price: Float,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int,
)
