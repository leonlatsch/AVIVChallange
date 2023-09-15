package dev.leonlatsch.avivchallange.listings.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingsResponse(
    val items: List<ListingResponse>,
    val totalCount: Int,
)