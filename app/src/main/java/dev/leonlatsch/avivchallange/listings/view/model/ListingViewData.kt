package dev.leonlatsch.avivchallange.listings.view.model

data class ListingViewData(
    val id: Int,
    val bedrooms: String?,
    val city: String,
    val area: String,
    val url: String?,
    val price: String,
    val professional: String,
    val propertyType: String,
    val rooms: String?,
)