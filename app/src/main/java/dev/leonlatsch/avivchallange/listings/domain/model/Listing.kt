package dev.leonlatsch.avivchallange.listings.domain.model

data class Listing(
    val id: Int,
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