package dev.leonlatsch.avivchallange.listings.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.leonlatsch.avivchallange.listings.data.local.model.ListingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {

    @Query("SELECT * FROM listing")
    fun selectListings(): Flow<List<ListingEntity>>

    @Query("SELECT * FROM listing WHERE id = :listingId")
    fun selectListingDetail(listingId: Int): Flow<ListingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListings(listings: List<ListingEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListingDetail(listings: ListingEntity)
}