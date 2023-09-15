package dev.leonlatsch.avivchallange.listings.data.local

import androidx.room.Dao
import androidx.room.Query
import dev.leonlatsch.avivchallange.listings.data.local.model.ListingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {

    @Query("SELECT * FROM listing")
    fun selectListings(): Flow<List<ListingDao>>

    @Query("SELECT * FROM listing WHERE id = :listingId")
    fun selectListingDetail(listingId: Int): Flow<ListingEntity>
}