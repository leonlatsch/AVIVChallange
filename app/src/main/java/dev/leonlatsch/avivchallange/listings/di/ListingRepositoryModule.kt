package dev.leonlatsch.avivchallange.listings.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonlatsch.avivchallange.listings.data.ListingRepositoryImpl
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class ListingRepositoryModule {

    @Binds
    abstract fun bindListingRepository(listingRepositoryImpl: ListingRepositoryImpl): ListingRepository
}