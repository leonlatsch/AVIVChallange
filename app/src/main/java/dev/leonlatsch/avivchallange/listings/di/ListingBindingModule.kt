package dev.leonlatsch.avivchallange.listings.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.leonlatsch.avivchallange.core.Mapper
import dev.leonlatsch.avivchallange.listings.data.ListingRepositoryImpl
import dev.leonlatsch.avivchallange.listings.data.mapper.ListingResponseToDomainMapper
import dev.leonlatsch.avivchallange.listings.data.remote.model.ListingResponse
import dev.leonlatsch.avivchallange.listings.domain.ListingRepository
import dev.leonlatsch.avivchallange.listings.domain.model.Listing

@Module
@InstallIn(SingletonComponent::class)
abstract class ListingBindingModule {

    @Binds
    abstract fun bindListingRepository(impl: ListingRepositoryImpl): ListingRepository

    @Binds
    abstract fun bindListingResponseToDomainMapper(impl: ListingResponseToDomainMapper): Mapper<ListingResponse, Listing>
}