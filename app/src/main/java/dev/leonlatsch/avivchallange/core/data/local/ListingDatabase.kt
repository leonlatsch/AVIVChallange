package dev.leonlatsch.avivchallange.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.leonlatsch.avivchallange.listings.data.local.ListingDao
import dev.leonlatsch.avivchallange.listings.data.local.model.ListingEntity

private const val VERSION = 1
const val DATABASE_NAME = "listing_database"

@Database(
    entities = [ListingEntity::class],
    version = VERSION,
    exportSchema = false,
)
abstract class ListingDatabase : RoomDatabase() {

    abstract fun listingDao(): ListingDao
}