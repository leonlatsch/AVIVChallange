package dev.leonlatsch.avivchallange.listings.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listing")
data class ListingEntity(
    @PrimaryKey val id: Int,
    val bedrooms: Int?,
    val city: String,
    val area: Float,
    val url: String?,
    val price: Float,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int?,
)