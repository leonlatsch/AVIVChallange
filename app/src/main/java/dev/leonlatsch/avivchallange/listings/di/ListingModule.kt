package dev.leonlatsch.avivchallange.listings.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonlatsch.avivchallange.core.data.local.ListingDatabase
import dev.leonlatsch.avivchallange.listings.data.local.ListingDao
import dev.leonlatsch.avivchallange.listings.data.remote.ListingApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ListingModule {

    @Provides
    @Singleton
    fun provideListingApi(retrofit: Retrofit): ListingApi = retrofit.create(ListingApi::class.java)

    @Provides
    @Singleton
    fun provideListingDao(database: ListingDatabase): ListingDao = database.listingDao()
}